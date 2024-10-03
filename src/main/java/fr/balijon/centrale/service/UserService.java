package fr.balijon.centrale.service;


import fr.balijon.centrale.entity.Address;
import fr.balijon.centrale.entity.Role;
import fr.balijon.centrale.entity.User;

import fr.balijon.centrale.entity.dto.UserDTO;
import fr.balijon.centrale.entity.dto.UserUpdateDTO;
import fr.balijon.centrale.exception.entity.EntityException;
import fr.balijon.centrale.repository.AddressRepository;
import fr.balijon.centrale.repository.UserRepository;
import fr.balijon.centrale.service.interfaces.ServiceListInterface;
import fr.balijon.centrale.exception.entity.user.ActivationCodeException;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeoutException;

@Service
@AllArgsConstructor
public class UserService implements ServiceListInterface<User, String, UserDTO, UserUpdateDTO>, UserDetailsService {

    public UserRepository userRepository;
    public RoleService roleService;
    public AddressRepository addressRepository;
    public PasswordEncoder passwordEncoder;

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public User create(@Valid UserDTO o) {
            User user = new User();
            List<Role> roles = new ArrayList<Role>();
            Role roleUser = roleService.findOneByLabel("ROLE_USER");
            roles.add(roleUser);
            user.setRoles(roles);
            user.setCreatedAt(LocalDateTime.now());
            user.setActivationCode(UUID.randomUUID().toString());
            user.setPhone(o.getPhone());
            user.setPassword(passwordEncoder.encode(o.getPassword()));
            user.setEmail(o.getEmail());
            user.setBirthAt(o.getBirthAt());
            user.setActivationCodeSentAt(LocalDateTime.now());
            user.setAddress(addressRepository.findById(o.getAddressId()).orElseThrow( () -> new EntityException("Address n'est pas trouvé avec id : " + o.getAddressId())));
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
        if(o.getAddressId() != null) {
            user.setAddress(addressRepository.findById(o.getAddressId()).orElseThrow( () -> new EntityException("Address n'est pas trouvé avec id : " + o.getAddressId())));
        }
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

            if (user.getAddress() != null) {
                Address address = user.getAddress();
                address.setUser(null);
                addressRepository.save(address);
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
        if (current.isAfter(user.getActivationCodeSentAt().plusMinutes(60))) {
            throw new ActivationCodeException("La durée du code d'activation a expiré");
        }
        user.setActivationCode(null);
        user.setActivationCodeSentAt(null);
        return userRepository.saveAndFlush(user);
}


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAndActivationCodeIsNull(username).orElseThrow(()-> new UsernameNotFoundException("User n'existe pas"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
    }


    public User findOneByEmail(String name) {
    return  userRepository.findOneByEmail(name).orElseThrow(() -> new EntityException("Pas trouvé l'user email"));
    }
}
