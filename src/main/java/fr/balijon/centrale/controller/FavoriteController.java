package fr.balijon.centrale.controller;

import fr.balijon.centrale.embedded.UserListingId;
import fr.balijon.centrale.service.FavoriteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/favorite", name = "app_favorite_")
public class FavoriteController {

    private FavoriteService favoriteService;

    @PostMapping(name = "switch")
    public Boolean create(@Valid @RequestBody UserListingId favorite)
    {
        return favoriteService.switchFavorite(favorite);
    }


//   @DeleteMapping(name="delete")
//    public Boolean delete(@RequestParam String userUuid, @RequestParam String listingUuid)
//   {
//
//       return favoriteService.delete(userUuid, listingUuid);
//   }


}
