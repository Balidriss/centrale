package fr.balijon.centrale.services;


import fr.balijon.centrale.entity.User;
import fr.balijon.centrale.entity.dto.UserDTO;
import fr.balijon.centrale.repository.UserRepository;
import fr.balijon.centrale.services.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
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
        User user = new User();
        user.setFavorites(o.getFavorites());
        user.setListings(o.getListings());
        user.setPassword(o.getPassword());
        user.setRole(o.getRole());
        user.setEmail(o.getEmail());
        user.setPhone(o.getPhone());
        user.setActivationCode(o.getActivationCode());
        user.setPhoto(o.getPhoto());
        user.setSiret(o.getSiret());
        user.setBirthAt(o.getBirthAt());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User update(UserDTO o, String id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setFavorites(o.getFavorites());
        user.setListings(o.getListings());
        user.setPassword(o.getPassword());
        user.setRole(o.getRole());
        user.setEmail(o.getEmail());
        user.setPhone(o.getPhone());
        user.setActivationCode(o.getActivationCode());
        user.setPhoto(o.getPhoto());
        user.setSiret(o.getSiret());
        user.setBirthAt(o.getBirthAt());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(String id) {
        userRepository.delete(findOneById(id));
    }


    @Override
    public User findOneById(String id) {
        return  userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
