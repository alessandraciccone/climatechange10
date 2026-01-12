package aciccone.climatechange10.service;

import aciccone.climatechange10.entities.User;
import aciccone.climatechange10.payloads.AuthRequestDTO;
import aciccone.climatechange10.payloads.UserResponseDTO;
import aciccone.climatechange10.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository= userRepository;
        this.passwordEncoder= passwordEncoder;
    }

public UserResponseDTO register(AuthRequestDTO dto){
        if(userRepository.findByEmail(dto.email()).isPresent()){
            throw new RuntimeException("Email già registrata");
        }
        User user = new User();
        user.setName(dto.name());
        user.setLastname(dto.lastname());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        userRepository.save(user);
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail()
        );
}
public UserResponseDTO login(String email, String password){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Credenziali non valide"));
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw  new RuntimeException("Credenziali non valide");
        }
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail()
        );

}
}


