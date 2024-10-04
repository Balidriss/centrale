package fr.balijon.centrale.repository;

import fr.balijon.centrale.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findOneByLabel(String roleUser);
}
