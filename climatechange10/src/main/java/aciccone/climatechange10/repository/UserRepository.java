package aciccone.climatechange10.repository;

import aciccone.climatechange10.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByName(String name);

}
