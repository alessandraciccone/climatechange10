package aciccone.climatechange10.service;

import aciccone.climatechange10.entities.Preferiti;
import aciccone.climatechange10.entities.User;
import aciccone.climatechange10.exception.NotFoundException;
import aciccone.climatechange10.payloads.PreferitiDTO;
import aciccone.climatechange10.payloads.PreferitiResponseDTO;
import aciccone.climatechange10.repository.PreferitiRepository;
import aciccone.climatechange10.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PreferitiService {

    private final PreferitiRepository preferitiRepository;
    private final UserRepository userRepository;

    public PreferitiService(
            PreferitiRepository preferitiRepository,
            UserRepository userRepository
    ) {
        this.preferitiRepository = preferitiRepository;
        this.userRepository = userRepository;
    }

    /* =========================
       GET ALL (per utente)
       ========================= */
    public List<PreferitiResponseDTO> getAllByUser(String email) {
        User user = getUserByEmail(email);

        return preferitiRepository.findByUser(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    /* =========================
       CREATE
       ========================= */
    public PreferitiResponseDTO createPreferito(PreferitiDTO dto, String email) {
        User user = getUserByEmail(email);

        // Evita duplicati (opzionale ma consigliato)
        if (preferitiRepository.existsByTitoloAndUser(dto.titolo(), user)) {
            throw new IllegalArgumentException("Preferito già esistente");
        }

        Preferiti preferito = new Preferiti();
        preferito.setTitolo(dto.titolo());
        preferito.setDescrizione(dto.descrizione());
        preferito.setImglink(dto.imglink());
        preferito.setUser(user);

        Preferiti saved = preferitiRepository.save(preferito);
        return toResponse(saved);
    }

    /* =========================
       DELETE (sicuro)
       ========================= */
    public void deletePreferito(Long id, String email) {
        User user = getUserByEmail(email);

        Preferiti preferito = preferitiRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new NotFoundException("Preferito non trovato o non autorizzato")
                );

        preferitiRepository.delete(preferito);
    }

    /* =========================
       UTILS
       ========================= */
    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new NotFoundException("Utente con email " + email + " non trovato")
                );
    }

    private PreferitiResponseDTO toResponse(Preferiti p) {
        return new PreferitiResponseDTO(
                p.getId(),
                p.getTitolo(),
                p.getDescrizione(),
                p.getImglink()
        );
    }
}
