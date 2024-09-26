package fr.balijon.centrale.services;


import fr.balijon.centrale.entity.Favorite;
import fr.balijon.centrale.entity.dto.FavoriteDTO;
import fr.balijon.centrale.repository.FavoriteRepository;
import fr.balijon.centrale.services.interfaces.BasicServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FavoriteService implements BasicServiceInterface<Favorite, Long, FavoriteDTO> {

    public FavoriteRepository favoriteRepository;
    public UserService userService;
    public ListingService listingService;

    public Favorite create(FavoriteDTO o) {
        Favorite favorite = new Favorite();
        favorite.setUser(userService.findOneById(o.getUserId()));
        favorite.setListing(listingService.findOneById(o.getListingId()));
        favorite.setCreatedAt(LocalDateTime.now());
        return favoriteRepository.saveAndFlush(favorite);
    }

    @Override
    public Boolean delete(Long id) {
        favoriteRepository.delete(favoriteRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        return true;
    }

//    public Boolean delete(String userUuid, String listingUuid) {
//        favoriteRepository.deleteByUserUuidAndListingUuid(userUuid, listingUuid);
//        return true;
//    }

}
