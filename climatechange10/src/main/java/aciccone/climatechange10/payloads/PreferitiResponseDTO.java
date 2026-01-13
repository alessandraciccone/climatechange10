package aciccone.climatechange10.payloads;

public record PreferitiResponseDTO(
        Long id,
        String titolo,
        String descrizione,
        String imglink // tutto minuscolo per uniformità
) { }