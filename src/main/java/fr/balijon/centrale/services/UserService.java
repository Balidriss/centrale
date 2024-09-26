package fr.balijon.centrale.services;


import fr.balijon.centrale.entity.Address;
import fr.balijon.centrale.entity.User;
import fr.balijon.centrale.entity.dto.UserDTO;
import fr.balijon.centrale.entity.dto.UserUpdateDTO;
import fr.balijon.centrale.repository.UserRepository;
import fr.balijon.centrale.services.interfaces.ServiceListInterface;
import fr.balijon.centrale.exception.ActivationCodeException;

import jakarta.persistence.EntityNotFoundException;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
@AllArgsConstructor
public class UserService implements ServiceListInterface<User, String, UserDTO, UserUpdateDTO> {

    public UserRepository userRepository;
    public AddressService addressService;

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO o) {
        User user = new User();
        user.setRoles("[\"ROLE_USER\"]");
        user.setCreatedAt(LocalDateTime.now());
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPhone(o.getPhone());
        user.setPassword(o.getPassword());
        user.setEmail(o.getEmail());
        user.setBirthAt(o.getBirthAt());
        // Send mail ?
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User update(UserUpdateDTO o, String id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setPhoto(o.getPhoto());
        user.setSiret(o.getSiret());
        user.setPhone(o.getPhone());
        user.setBirthAt(o.getBirthAt());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Boolean delete(String id) {
        try {
            User user = findOneById(id);
            user.setPhone(null);
            user.setBirthAt(null);
            user.setPhoto(null);
            user.setSiret(null);
            user.setEmail("Utilisateur supprimé");

            // call service ?
            Address address = user.getAddress();
            if (address!= null) {
                address.setUser(null);
                addressService.update(address,address.getId());
            }
            userRepository.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public User findOneById(String id) {
        return  userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User activate(String code)  throws TimeoutException {
        User user = userRepository.findUserByActivationCode(code)
                .orElseThrow(ActivationCodeException::new);

        LocalDateTime current = LocalDateTime.now();
        if (current.isAfter(user.getActivationCodeSentAt().plusMinutes(15))) {
            throw new TimeoutException("La durée du code a expiré");
        }
        user.setActivationCode(null);
        user.setActivationCodeSentAt(null);
        return userRepository.saveAndFlush(user);
}}
