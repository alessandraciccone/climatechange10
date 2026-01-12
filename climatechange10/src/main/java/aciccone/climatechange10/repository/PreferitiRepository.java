package aciccone.climatechange10.repository;

import aciccone.climatechange10.entities.Preferiti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreferitiRepository extends JpaRepository<Preferiti, Long> {
    List<Preferiti> findByTitolo(String titolo);
}