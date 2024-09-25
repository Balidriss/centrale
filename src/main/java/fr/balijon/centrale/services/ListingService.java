package fr.balijon.centrale.services;

import fr.balijon.centrale.entity.Listing;
import fr.balijon.centrale.services.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO

@Service
@AllArgsConstructor
public class ListingService implements ServiceListInterface<Listing, Long, Listing, Listing> {

    public ListingRepository listingRepository;

    @Override
    public List<Listing> list() {
        return listingRepository.findAll();
    }

    @Override
    public Listing create(Listing o) {
        Listing listing = new Listing();

        return listingRepository.saveAndFlush(listing);
    }

    @Override
    public Listing update(Listing o, Long id) {

        return listingRepository.saveAndFlush(listing);
    }

    @Override
    public void delete(String id) {
        listingRepository.delete(findOneById(id));
    }


    @Override
    public Listing findOneById(String id) {
        return  listingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
