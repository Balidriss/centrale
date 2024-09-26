package fr.balijon.centrale.services;


import fr.balijon.centrale.entity.Favorite;
import fr.balijon.centrale.entity.dto.FavoriteDTO;
import fr.balijon.centrale.repository.FavoriteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoriteService {

    public FavoriteRepository favoriteRepository;
    public UserService userService;
    public ListingService listingService;

    public Favorite create(FavoriteDTO o) {
        Favorite favorite = new Favorite();

        favorite.setUser(userService.findOneById(o.getUserId()));
        favorite.setListing(listingService.findOneById(o.getListingId()));
        return favoriteRepository.saveAndFlush(favorite);
    }

    public Boolean delete(String userUuid, String listingUuid) {
        favoriteRepository.deleteByUserUuidAndListingUuid(userUuid, listingUuid);
        return true;
    }

}
