package fr.balijon.centrale.controller;

import fr.balijon.centrale.entity.Favorite;
import fr.balijon.centrale.services.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/favorite", name = "app_favorite_")
public class FavoriteController {

    private FavoriteService favoriteService;

    @GetMapping(name = "list")
    public List<Favorite> list()
    {
        return favoriteService.list();
    }

    @GetMapping(value = "/{id}", name = "show")
    public Favorite show(@PathVariable Long id)
    {
        return favoriteService.findOneById(id);
    }


    @PostMapping(name = "create")
    public Favorite create(@RequestBody Favorite favorite)
    {
        return favoriteService.create(favorite);
    }

    @PutMapping(value = "/{id}", name = "update")
    public Favorite update(@PathVariable Long id, @RequestBody Favorite favorite)
    {
        return favoriteService.update(favorite, id);
    }
}
