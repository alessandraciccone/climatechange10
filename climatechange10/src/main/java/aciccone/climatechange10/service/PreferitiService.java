package aciccone.climatechange10.service;

import aciccone.climatechange10.entities.Preferiti;
import aciccone.climatechange10.exception.NotFoundException;
import aciccone.climatechange10.payloads.PreferitiDTO;
import aciccone.climatechange10.repository.PreferitiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferitiService {

    @Autowired
    private PreferitiRepository preferitiRepository;

    // GET ALL - Ottieni tutti i preferiti
    public List<Preferiti> getAllPreferiti() {
        return preferitiRepository.findAll();
    }

    // GET BY ID - Ottieni un preferito per ID
    public Preferiti getPreferitoById(Long id) {
        return preferitiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Preferito con id " + id + " non trovato"));
    }

    // GET BY TITOLO - Ottieni preferiti per titolo
    public List<Preferiti> getPreferitiByTitolo(String titolo) {
        return preferitiRepository.findByTitolo(titolo);
    }

    // POST - Crea un nuovo preferito
    public Preferiti createPreferito(PreferitiDTO body) {
        Preferiti newPreferito = new Preferiti(
                body.titolo(),
                body.descrizione(),
                body.imglink()
        );
        return preferitiRepository.save(newPreferito);
    }

    // PUT - Aggiorna un preferito esistente
    public Preferiti updatePreferito(Long id, PreferitiDTO body) {
        Preferiti found = this.getPreferitoById(id);

        found.setTitolo(body.titolo());
        found.setDescrizione(body.descrizione());
        found.setImglink(body.imglink());

        return preferitiRepository.save(found);
    }

    // DELETE - Elimina un preferito
    public void deletePreferito(Long id) {
        Preferiti found = this.getPreferitoById(id);
        preferitiRepository.delete(found);
    }
}