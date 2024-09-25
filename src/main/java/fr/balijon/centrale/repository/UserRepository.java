package fr.balijon.centrale.repository;

import fr.balijon.centrale.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
