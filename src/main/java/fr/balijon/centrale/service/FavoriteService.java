package fr.balijon.centrale.service;


import fr.balijon.centrale.embedded.UserListingId;
import fr.balijon.centrale.entity.Favorite;
import fr.balijon.centrale.repository.FavoriteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FavoriteService {

    public FavoriteRepository favoriteRepository;
    public UserService userService;
    public ListingService listingService;

    public Boolean switchFavorite(UserListingId o) {

        if(favoriteRepository.findById(o).isEmpty())
        {
            Favorite favorite = new Favorite();
            favorite.setId(o);
        //    favorite.setUser(userService.findOneById(o.getUserUuid()));
        //    favorite.setListing(listingService.findOneById(o.getListingUuid()));
            favorite.setCreatedAt(LocalDateTime.now());
            favoriteRepository.saveAndFlush(favorite);
            return true;

        }
        favoriteRepository.delete(favoriteRepository.findById(o).get());
        return false;

    }


    //public Boolean delete(Long id) {
    //    favoriteRepository.delete(favoriteRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    //    return true;
    //}

//    public Boolean delete(String userUuid, String listingUuid) {
//        favoriteRepository.deleteByUserUuidAndListingUuid(userUuid, listingUuid);
//        return true;
//    }

}
