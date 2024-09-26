package fr.balijon.centrale.controller;

import fr.balijon.centrale.entity.Favorite;
import fr.balijon.centrale.entity.dto.FavoriteDTO;
import fr.balijon.centrale.services.FavoriteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/favorite", name = "app_favorite_")
public class FavoriteController {

    private FavoriteService favoriteService;

    @PostMapping(name = "create")
    public Favorite create(@Valid @RequestBody FavoriteDTO favorite)
    {
        return favoriteService.create(favorite);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return favoriteService.delete(id);
    }

//   @DeleteMapping(name="delete")
//    public Boolean delete(@RequestParam String userUuid, @RequestParam String listingUuid)
//   {
//
//       return favoriteService.delete(userUuid, listingUuid);
//   }


}
