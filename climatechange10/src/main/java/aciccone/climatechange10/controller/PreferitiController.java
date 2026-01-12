package aciccone.climatechange10.controller;

import aciccone.climatechange10.entities.Preferiti;
import aciccone.climatechange10.service.PreferitiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preferiti")
public class PreferitiController {
    private final PreferitiService preferitiService;

    public PreferitiController(PreferitiService preferitiService) {
        this.preferitiService = preferitiService;
    }

    @GetMapping
    public List<Preferiti> getAll() {
        return preferitiService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Preferiti> getbyId(@PathVariable Long id) {
        return preferitiService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Preferiti> create(@RequestBody Preferiti preferito) {
        Preferiti created = preferitiService.create(preferito);
        return ResponseEntity.status(201).body(created);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        preferitiService.delete(id);
        return ResponseEntity.noContent().build();
    }
}