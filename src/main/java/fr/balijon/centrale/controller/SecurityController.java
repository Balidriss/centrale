package fr.balijon.centrale.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.balijon.centrale.configuration.security.JwtAuthenticatorService;
import fr.balijon.centrale.entity.User;
import fr.balijon.centrale.entity.dto.LoginRequest;
import fr.balijon.centrale.entity.dto.UserDTO;
import fr.balijon.centrale.jsonviews.JsonViews;
import fr.balijon.centrale.responses.JwtResponse;
import fr.balijon.centrale.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SecurityController {

    private UserService userService;
    private JwtAuthenticatorService jwtAuthenticatorService;

    @PostMapping("/api/register")
    @JsonView(JsonViews.UserShow.class)
    public User register(@Valid @RequestBody UserDTO user) {
        return userService.create(user);
    }

    @PostMapping("/api/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest user) {
        return jwtAuthenticatorService.authenticate(user);
    }

}
