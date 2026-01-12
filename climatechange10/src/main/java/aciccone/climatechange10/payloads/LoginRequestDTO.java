package aciccone.climatechange10.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank (message = "email cannot be blank")
        @Email
        String email,
        @NotBlank(message = "password cannot be blank")
        String password
) {
}
