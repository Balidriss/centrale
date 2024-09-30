package fr.balijon.centrale.services;


import fr.balijon.centrale.entity.Address;
import fr.balijon.centrale.entity.User;
import fr.balijon.centrale.entity.dto.LoginRequest;
import fr.balijon.centrale.entity.dto.UserDTO;
import fr.balijon.centrale.entity.dto.UserUpdateDTO;
import fr.balijon.centrale.exception.entity.EntityException;
import fr.balijon.centrale.repository.UserRepository;
import fr.balijon.centrale.services.interfaces.ServiceListInterface;
import fr.balijon.centrale.exception.entity.user.ActivationCodeException;

import jakarta.validation.Valid;
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
    public User create(@Valid UserDTO o) {
            User user = new User();
            user.setRoles("[\"ROLE_USER\"]");
            user.setCreatedAt(LocalDateTime.now());
            user.setActivationCode(UUID.randomUUID().toString());
            user.setPhone(o.getPhone());
            user.setPassword(o.getPassword());
            user.setEmail(o.getEmail());
            user.setBirthAt(o.getBirthAt());
            user.setActivationCodeSentAt(LocalDateTime.now());
            // Send mail ?
            return userRepository.saveAndFlush(user);

    }

    @Override
    public User update(@Valid UserUpdateDTO o, String id) {
        User user = userRepository.findById(id).orElseThrow( () -> new EntityException("User n'est pas trouvé avec id : " + id,o));
        user.setPhoto(o.getPhoto());
        user.setSiret(o.getSiret());
        user.setPhone(o.getPhone());
        user.setBirthAt(o.getBirthAt());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Boolean delete(String id) {

            User user = userRepository.findById(id).orElseThrow( () -> new EntityException("User n'est pas trouvé avec id : " + id));
            user.setPhone(null);
            user.setBirthAt(null);
            user.setPhoto(null);
            user.setSiret(null);
            user.setEmail("Utilisateur supprimé");
            user.setActivationCodeSentAt(null);
            user.setActivationCode(null);
            // call service ?
            Address address = user.getAddress();
            if (address!= null) {
                address.setUser(null);
                addressService.update(address,address.getId());
            }
            userRepository.saveAndFlush(user);
            return true;
    }


    @Override
    public User findOneById(String id) {
        return  userRepository.findById(id).orElseThrow( () -> new EntityException("User n'est pas trouvé avec id : " + id));
    }

    public User activate(String code)  throws TimeoutException {
        User user = userRepository.findUserByActivationCode(code)
                .orElseThrow(() -> new ActivationCodeException("Code d'activation erroné"));

        LocalDateTime current = LocalDateTime.now();
        //2 minutes for test
        if (current.isAfter(user.getActivationCodeSentAt().plusMinutes(2))) {
            throw new ActivationCodeException("La durée du code d'activation a expiré");
        }
        user.setActivationCode(null);
        user.setActivationCodeSentAt(null);
        return userRepository.saveAndFlush(user);
}

    public String login(LoginRequest loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        User user = userRepository.findByEmailAndPassword(email,password).orElseThrow(() -> new EntityException("User n'existe pas !"));
        return user.getUuid();
    }
}
