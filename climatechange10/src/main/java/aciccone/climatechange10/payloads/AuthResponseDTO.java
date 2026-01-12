package aciccone.climatechange10.payloads;

public record AuthResponseDTO(
        String token,
        String type,
        UserResponseDTO user
) {
}
