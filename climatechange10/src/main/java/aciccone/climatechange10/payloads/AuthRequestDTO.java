package aciccone.climatechange10.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
    @NotBlank(message = "name cannot be blank")
    String name,
    @NotBlank(message = "Lastname cannot be blank")
    String lastname,
    @NotBlank(message = "email cannot be blank")
    @Email (message = "email must be valid")
    String email,
    @NotBlank(message = "password cannot be blank")
    String password
) {
}
