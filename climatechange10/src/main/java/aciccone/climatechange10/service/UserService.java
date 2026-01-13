package aciccone.climatechange10.service;

import aciccone.climatechange10.entities.User;
import aciccone.climatechange10.exception.NotFoundException;
import aciccone.climatechange10.exception.ValidationExceptions;
import aciccone.climatechange10.payloads.UserResponseDTO;
import aciccone.climatechange10.payloads.UserUpdateDTO;
import aciccone.climatechange10.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }


    // GET ALL
    public List<UserResponseDTO> findAll(){
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // GET BY ID
    public UserResponseDTO getById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente con id " + id + " non è stato trovato"));
        return toResponse(user);
    }

    // UPDATE USER
    // In UserService.java
    public UserResponseDTO update(Long id, UserUpdateDTO dto) {
        if(dto.name().length() < 2){
            throw new ValidationExceptions("Il nome deve avere almeno due caratteri");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente con id " + id + " non è stato trovato"));

        user.setName(dto.name());
        user.setEmail(dto.email());

        userRepository.save(user);
        return toResponse(user);
    }


    // DELETE
    public void delete(Long id){
        if(!userRepository.existsById(id)){
            throw new NotFoundException("Utente con id " + id + " non è stato trovato");
        }
        userRepository.deleteById(id);
    }


    private UserResponseDTO toResponse(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLastname(),

                user.getEmail()
        );
    }
}
