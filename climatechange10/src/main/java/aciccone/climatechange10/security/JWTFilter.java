package aciccone.climatechange10.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTTool jwtTool;
    private final UserDetailsService userDetailsService;

    public JWTFilter(JWTTool jwtTool, UserDetailsService userDetailsService) {
        this.jwtTool = jwtTool;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        System.out.println("===== JWT FILTER =====");
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Method: " + request.getMethod());

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            System.out.println("Token estratto: " + token.substring(0, Math.min(20, token.length())) + "...");

            try {
                if (jwtTool.validateToken(token)) {
                    System.out.println("Token VALIDO");

                    String email = jwtTool.getEmailFromToken(token);
                    System.out.println("Email dal token: " + email);

                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    System.out.println("UserDetails caricato: " + userDetails.getUsername());
                    System.out.println("Authorities: " + userDetails.getAuthorities());

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("Autenticazione impostata con successo");
                } else {
                    System.out.println("Token NON VALIDO");
                }
            } catch (Exception e) {
                System.out.println("ERRORE durante la validazione del token: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Nessun token trovato nell'header");
        }

        filterChain.doFilter(request, response);
    }
}
