package fr.balijon.centrale.repository;

import fr.balijon.centrale.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    void deleteByUserUuidAndListingUuid(String userUuid, String listingUuid);
}
