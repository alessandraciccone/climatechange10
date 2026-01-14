package aciccone.climatechange10.controller;

import aciccone.climatechange10.payloads.PreferitiDTO;
import aciccone.climatechange10.payloads.PreferitiResponseDTO;
import aciccone.climatechange10.service.PreferitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferiti")
@CrossOrigin
public class PreferitiController {

    @Autowired
    private PreferitiService preferitiService;

    @GetMapping
    public List<PreferitiResponseDTO> getAll(@AuthenticationPrincipal UserDetails user) {
        return preferitiService.getAllByUser(user.getUsername());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PreferitiResponseDTO create(
            @RequestBody @Validated PreferitiDTO body,
            @AuthenticationPrincipal UserDetails user
    ) {
        return preferitiService.createPreferito(body, user.getUsername());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails user) {
        preferitiService.deletePreferito(id, user.getUsername());
    }
}
