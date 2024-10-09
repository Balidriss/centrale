package fr.balijon.centrale.service;




import fr.balijon.centrale.entity.Fuel;
import fr.balijon.centrale.entity.Listing;
import fr.balijon.centrale.entity.dto.ListingDTO;
import fr.balijon.centrale.entity.dto.ListingListDTO;
import fr.balijon.centrale.entity.dto.ListingUpdateDTO;
import fr.balijon.centrale.exception.entity.EntityException;
import fr.balijon.centrale.repository.ListingRepository;
import fr.balijon.centrale.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ListingService  {

    public ListingRepository listingRepository;
    public ModelService modelService;
    public FuelService fuelService;
    public UserService userService;
    public AddressService addressService;


    //public List<Listing> list() {
    //    return listingRepository.findAll();
    //}

    public Page<ListingListDTO> list(Pageable pageable) {
//        listingRepository.findAll(Sort.by("createdAt").descending());
        Page<Listing> listings = listingRepository.findAll(pageable);
        List<ListingListDTO> listDTOs = new ArrayList<>();
        listings.forEach((item) -> {
            listDTOs.add(new ListingListDTO(
                    item.getUuid(),
                    item.getTitle(),
                    item.getPriceDecimal(),
                    item.getMileage(),
                    item.getProducedAt(),
                    item.getAddress().getZipCode(),
                    item.getModel().getName(),
                    item.getModel().getBrand().getName(),
                    item.getFuel().getType()
            ));
        });
        return new PageImpl<>(listDTOs, pageable, listings.getTotalElements());
    }

    public Listing create(ListingDTO o, Principal currentUser) {
        Listing listing = new Listing();

        listing.setModel(modelService.findOneById(o.getModelId()));
        listing.setCreatedAt(LocalDateTime.now());
        listing.setDescription(o.getDescription());
        listing.setFuel(fuelService.findOneById(o.getFuelId()));
        listing.setPrice(o.getPrice());
        listing.setProducedAt(o.getProducedAt());
        listing.setOwner(userService.findOneByEmail(currentUser.getName()));
        listing.setAddress(addressService.findOneById(o.getAddressId()));
        listing.setMileage(o.getMileage());
        listing.initTitle();
        return listingRepository.saveAndFlush(listing);
    }

    public Listing update(ListingUpdateDTO o, String id) {
        Listing listing = listingRepository.findById(id).orElseThrow(() -> new EntityException("Lisintg n'est pas trouvé avec id : " + id,o));
        listing.setDescription(o.getDescription());
        listing.setPrice(o.getPrice());
        listing.setMileage(o.getMileage());
        return listingRepository.saveAndFlush(listing);
    }

    public Boolean delete(String id) {
        try {
            Listing listing = listingRepository.findById(id).orElseThrow(() -> new EntityException("Lisintg n'est pas trouvé avec id : " + id));
            listing.setTitle("Annonce supprimé");
            listing.setDescription("Annonce supprimé");
            listing.setMileage(0L);
            listing.setPrice(0L);
            listing.setProducedAt(LocalDateTime.now());
            //delete images
            return true;
        } catch (EntityException e) {
            return false;
        }
    }

    public Listing findOneById(String id) {
        return listingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Page<ListingListDTO> search(Pageable pageable) {

    }
}
