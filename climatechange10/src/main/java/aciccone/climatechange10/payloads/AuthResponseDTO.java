package aciccone.climatechange10.payloads;

public record AuthResponseDTO(
        long id,
        UserResponseDTO user,
        String token
){

}
