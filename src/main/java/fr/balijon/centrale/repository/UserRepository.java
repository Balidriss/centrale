package fr.balijon.centrale.repository;

import fr.balijon.centrale.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByActivationCode(String code);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmailAndActivationCodeIsNull(String username);
}
