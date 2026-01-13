package aciccone.climatechange10.controller;

import aciccone.climatechange10.entities.Preferiti;
import aciccone.climatechange10.payloads.PreferitiDTO;
import aciccone.climatechange10.payloads.PreferitiResponseDTO;
import aciccone.climatechange10.service.PreferitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/preferiti")
@CrossOrigin(origins = "*") // Per permettere chiamate dal frontend React
public class PreferitiController {

    @Autowired
    private PreferitiService preferitiService;

    // GET ALL - Ottieni tutti i preferiti
    @GetMapping
    public List<PreferitiResponseDTO> getAllPreferiti() {
        return preferitiService.getAllPreferiti().stream()
                .map(p -> new PreferitiResponseDTO(
                        p.getId(),
                        p.getTitolo(),
                        p.getDescrizione(),
                        p.getImglink()
                ))
                .collect(Collectors.toList());
    }

    // GET BY ID - Ottieni un preferito per ID
    @GetMapping("/{id}")
    public PreferitiResponseDTO getPreferitoById(@PathVariable Long id) {
        Preferiti found = preferitiService.getPreferitoById(id);
        return new PreferitiResponseDTO(
                found.getId(),
                found.getTitolo(),
                found.getDescrizione(),
                found.getImglink()
        );
    }

    // GET BY TITOLO - Cerca preferiti per titolo
    @GetMapping("/search")
    public List<PreferitiResponseDTO> searchByTitolo(@RequestParam String titolo) {
        return preferitiService.getPreferitiByTitolo(titolo).stream()
                .map(p -> new PreferitiResponseDTO(
                        p.getId(),
                        p.getTitolo(),
                        p.getDescrizione(),
                        p.getImglink()
                ))
                .collect(Collectors.toList());
    }

    // POST - Crea un nuovo preferito
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PreferitiResponseDTO createPreferito(@RequestBody @Validated PreferitiDTO body) {
        Preferiti created = preferitiService.createPreferito(body);
        return new PreferitiResponseDTO(
                created.getId(),
                created.getTitolo(),
                created.getDescrizione(),
                created.getImglink()
        );
    }

    // PUT - Aggiorna un preferito
    @PutMapping("/{id}")
    public PreferitiResponseDTO updatePreferito(@PathVariable Long id,
                                                @RequestBody @Validated PreferitiDTO body) {
        Preferiti updated = preferitiService.updatePreferito(id, body);
        return new PreferitiResponseDTO(
                updated.getId(),
                updated.getTitolo(),
                updated.getDescrizione(),
                updated.getImglink()
        );
    }

    // DELETE - Elimina un preferito
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePreferito(@PathVariable Long id) {
        preferitiService.deletePreferito(id);
    }
}