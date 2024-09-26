package fr.balijon.centrale.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.balijon.centrale.entity.User;
import fr.balijon.centrale.entity.dto.UserDTO;
import fr.balijon.centrale.entity.dto.UserUpdateDTO;
import fr.balijon.centrale.jsonviews.JsonViews;
import fr.balijon.centrale.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/user", name = "app_user_")
public class UserController {
    private UserService userService;

    @PostMapping
    @JsonView(JsonViews.UserShow.class)
    public User create(@RequestBody UserDTO dto) {
        return userService.create(dto);
    }

    @PutMapping("/{uuid}")
    @JsonView(JsonViews.UserShow.class)
    public User update(@RequestBody UserUpdateDTO dto, @PathVariable String uuid) {
        return userService.update(dto, uuid);
    }

    @GetMapping("/activate/{code}")
    @JsonView(JsonViews.UserShow.class)
    public User activate(@PathVariable String code) {
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
