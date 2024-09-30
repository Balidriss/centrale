package fr.balijon.centrale.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.balijon.centrale.entity.User;
import fr.balijon.centrale.entity.dto.LoginRequest;
import fr.balijon.centrale.entity.dto.UserDTO;
import fr.balijon.centrale.entity.dto.UserUpdateDTO;
import fr.balijon.centrale.jsonviews.JsonViews;
import fr.balijon.centrale.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/user", name = "app_user_")
public class UserController {
    private UserService userService;


    @PutMapping("/{uuid}")
    @JsonView(JsonViews.UserShow.class)
    public User update(@Valid @RequestBody UserUpdateDTO dto, @PathVariable String uuid) {
        return userService.update(dto, uuid);
    }

    @GetMapping("/activate/{code}")
    @JsonView(JsonViews.UserShow.class)
    public User activate(@PathVariable String code) throws TimeoutException {
        return userService.activate(code);
    }
    //    @GetMapping(value = "/{slug}", name = "show")
//    public User show(@PathVariable String slug)
//    {
//        return userService.findOneBySlug(slug);
//    }

    @GetMapping("/{uuid}")
    @JsonView(JsonViews.UserShow.class)
    public User show(@PathVariable String uuid) {
        return userService.findOneById(uuid);
    }

    @DeleteMapping("/{uuid}")
    public Boolean delete(@PathVariable String uuid) {
        return userService.delete(uuid);
    }


}

