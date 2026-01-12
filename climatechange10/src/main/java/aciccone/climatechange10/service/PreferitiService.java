package aciccone.climatechange10.service;

import aciccone.climatechange10.entities.Preferiti;
import aciccone.climatechange10.repository.PreferitiRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PreferitiService {
    private final PreferitiRepository preferitiRepository;

    public PreferitiService(PreferitiRepository preferitiRepository) {
        this.preferitiRepository = preferitiRepository;
    }

    public List<Preferiti> getAll() {
        return preferitiRepository.findAll();
    }

    public Optional<Preferiti> getById(Long id) {
        return preferitiRepository.findById(id);
    }

    public List<Preferiti> getByTitolo(String titolo) {
        return preferitiRepository.findByTitolo(titolo);
    }

    public Preferiti create(Preferiti preferito) {
        return preferitiRepository.save(preferito);
    }

    public Preferiti update(Long id, Preferiti updated) {
        return preferitiRepository.findById(id)
                .map(existing -> {
                    existing.setTitolo(updated.getTitolo());
                    existing.setDescrizione(updated.getDescrizione());
                    existing.setImglink(updated.getImglink());
                    return preferitiRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Preferito non trovato"));
    }

    public void delete(Long id) {
        preferitiRepository.deleteById(id);
    }
}



