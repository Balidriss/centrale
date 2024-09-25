package fr.balijon.centrale.controller;

import fr.balijon.centrale.entity.User;
import fr.balijon.centrale.entity.dto.UserDTO;
import fr.balijon.centrale.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/user", name = "app_user_")
public class UserController {

    private UserService userService;

    @GetMapping(name = "list")
    public List<User> list()
    {
        return userService.list();
    }

    @GetMapping(value = "/{uuid}", name = "show")
    public User show(@PathVariable String uuid)
    {
        return userService.findOneById(uuid);
    }
//    @GetMapping(value = "/{slug}", name = "show")
//    public User show(@PathVariable String slug)
//    {
//        return userService.findOneBySlug(slug);
//    }

    @PostMapping(name = "create")
    public User create(@RequestBody UserDTO userDTO)
    {
        return userService.create(userDTO);
    }

    @PutMapping(value = "/{id}", name = "update")
    public User update(@PathVariable String id, @RequestBody UserDTO userDTO)
    {
        return userService.update(userDTO, id);
    }
}
