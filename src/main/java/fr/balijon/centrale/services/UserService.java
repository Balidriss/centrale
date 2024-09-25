package fr.balijon.centrale.services;

import fr.balijon.centrale.entities.User;
import fr.balijon.centrale.repository.UserRepository;
import fr.balijon.centrale.services.interfaces.ServiceListInterface;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements ServiceListInterface<User, String, UserDTO, UserDTO> {

    public UserRepository userRepository;

}
