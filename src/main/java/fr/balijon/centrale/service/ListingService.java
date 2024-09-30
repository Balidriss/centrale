package fr.balijon.centrale.service;




import fr.balijon.centrale.entity.Listing;
import fr.balijon.centrale.repository.ListingRepository;
import fr.balijon.centrale.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ListingService implements ServiceListInterface<Listing, String, Listing, Listing> {

    public ListingRepository listingRepository;

    @Override
    public List<Listing> list() {
        return listingRepository.findAll();
    }

    @Override
    public Listing create(Listing o) {
        Listing listing = new Listing();
        listing.setFavorites(o.getFavorites());
        listing.setImages(o.getImages());
        listing.setModel(o.getModel());
        listing.setCreatedAt(LocalDateTime.now());
        listing.setDescription(o.getDescription());
        listing.setFuel(o.getFuel());
        listing.setPrice(o.getPrice());
        listing.setProducedAt(o.getProducedAt());
        listing.setTitle(o.getTitle());
        listing.setOwner(o.getOwner());
        listing.setAddress(o.getAddress());
        listing.setMileage(o.getMileage());
        return listingRepository.saveAndFlush(listing);
    }

    @Override
    public Listing update(Listing o, String id) {
        Listing listing = new Listing();
        return listingRepository.saveAndFlush(listing);
    }

    @Override
    public Boolean delete(String id) {
        listingRepository.delete(findOneById(id));
        return true;
    }

    @Override
    public Listing findOneById(String id) {
        return listingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


}
