package fr.balijon.centrale.service;


import fr.balijon.centrale.entity.Role;
import fr.balijon.centrale.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {

    public RoleRepository roleRepository;

    public Role findOneById(Integer id) {
        return  roleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Role findOneByLabel(String roleUser) {
        return  roleRepository.findOneByLabel(roleUser);
    }
}
