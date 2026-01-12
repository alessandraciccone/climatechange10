package aciccone.climatechange10.payloads;

public record AuthResponseDTO(
        UserResponseDTO user,
        String token
){

}
