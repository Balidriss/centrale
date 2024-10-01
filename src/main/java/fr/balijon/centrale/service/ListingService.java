package fr.balijon.centrale.service;




import fr.balijon.centrale.entity.Fuel;
import fr.balijon.centrale.entity.Listing;
import fr.balijon.centrale.entity.dto.ListingDTO;
import fr.balijon.centrale.entity.dto.ListingUpdateDTO;
import fr.balijon.centrale.exception.entity.EntityException;
import fr.balijon.centrale.repository.ListingRepository;
import fr.balijon.centrale.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ListingService  {

    public ListingRepository listingRepository;
    public ModelService modelService;
    public FuelService fuelService;
    public UserService userService;
    public AddressService addressService;


    public List<Listing> list() {
        return listingRepository.findAll();
    }

    public Listing create(ListingDTO o, Principal currentUser) {
        Listing listing = new Listing();

        listing.setModel(modelService.findOneById(o.getModelId()));
        listing.setCreatedAt(LocalDateTime.now());
        listing.setDescription(o.getDescription());
        listing.setFuel(fuelService.findOneById(o.getFuelId()));
        listing.setPrice(o.getPrice());
        listing.setProducedAt(o.getProducedAt());
        listing.setTitle(o.getTitle());
        listing.setOwner(userService.findOneByEmail(currentUser.getName()));
        listing.setAddress(addressService.findOneById(o.getAddressId()));
        listing.setMileage(o.getMileage());
        return listingRepository.saveAndFlush(listing);
    }

    public Listing update(ListingUpdateDTO o, String id) {
        Listing listing = listingRepository.findById(id).orElseThrow(() -> new EntityException("Lisintg n'est pas trouvé avec id : " + id,o));
        listing.setDescription(o.getDescription());
        listing.setPrice(o.getPrice());
        listing.setMileage(o.getMileage());
        listing.setTitle(o.getTitle());
        return listingRepository.saveAndFlush(listing);
    }

    public Boolean delete(String id) {
        listingRepository.delete(findOneById(id));
        return true;
    }

    public Listing findOneById(String id) {
        return listingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
