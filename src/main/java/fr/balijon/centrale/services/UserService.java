package fr.balijon.centrale.services;

import fr.balijon.centrale.entities.User;
import fr.balijon.centrale.entities.dto.UserDTO;
import fr.balijon.centrale.repository.UserRepository;
import fr.balijon.centrale.services.interfaces.ServiceListInterface;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements ServiceListInterface<User, String, UserDTO, UserDTO> {

    public UserRepository userRepository;

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO o) {
        User student = new User();
        //TODO
        return userRepository.saveAndFlush(student);
    }

    @Override
    public User update(UserDTO o, String id) {
        //TODO
        return null;
    }

    @Override
    public void delete(String id) {
        //TODO

    }

    @Override
    public User findOneById(String id) {
        //TODO
        return null;
    }
}
