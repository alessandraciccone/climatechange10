package aciccone.climatechange10.repository;

import aciccone.climatechange10.entities.Preferiti;
import aciccone.climatechange10.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PreferitiRepository extends JpaRepository<Preferiti, Long> {

    List<Preferiti> findByUser(User user);

    Optional<Preferiti> findByIdAndUser(Long id, User user);

    boolean existsByTitoloAndUser(String titolo, User user);
}
