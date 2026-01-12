package aciccone.climatechange10.payloads;

public record UserUpdateDTO(
        String name,
        String lastname,
        String email,
        String password
) {
}
