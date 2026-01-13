package aciccone.climatechange10.controller;

import aciccone.climatechange10.payloads.*;
import aciccone.climatechange10.security.JWTTool;
import aciccone.climatechange10.service.AuthService;
import aciccone.climatechange10.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    private final AuthService authService;
    private final UserService userService;
    private final JWTTool jwtTool;

    public UserController(AuthService authService, UserService userService, JWTTool jwtTool) {
        this.authService = authService;
        this.userService = userService;
        this.jwtTool = jwtTool;
    }


    @PostMapping("/auth/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody AuthRequestDTO dto) {
        UserResponseDTO userResponse = authService.register(dto);
        return ResponseEntity.ok(userResponse);
    }
    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        UserResponseDTO userResponse = authService.login(dto.email(), dto.password());
        String token = jwtTool.generateToken(userResponse.email());

        // Passa l'ID come primo parametro
        return ResponseEntity.ok(new AuthResponseDTO(
                userResponse.id(),  // <- Aggiungi l'ID qui
                userResponse,
                token
        ));
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("isAuthenticated()") // Cambia da hasRole('USER') a isAuthenticated()
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {
        System.out.println("===== GET USER BY ID =====");
        System.out.println("ID richiesto: " + id);
        System.out.println("Authentication: " + SecurityContextHolder.getContext().getAuthentication());

        UserResponseDTO userResponse = userService.getById(id);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> update(
            @PathVariable Long id,
            @RequestBody UserUpdateDTO dto
    ) {
        UserResponseDTO updated = userService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("Utente eliminato");
    }
}