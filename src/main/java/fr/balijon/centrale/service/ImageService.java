package fr.balijon.centrale.service;


import fr.balijon.centrale.entity.Image;
import fr.balijon.centrale.entity.User;
import fr.balijon.centrale.entity.dto.ImageDTO;
import fr.balijon.centrale.repository.ImageRepository;
import fr.balijon.centrale.service.interfaces.BasicServiceInterface;
import fr.balijon.centrale.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageService implements BasicServiceInterface<Image, String, ImageDTO> {

    public ImageRepository imageRepository;
    public ListingService listingService;



    @Override
    public Image create(ImageDTO o) {
        Image image = new Image();
        image.setPath(o.getPath());
        image.setListing(listingService.findOneById(o.getListingUuid()));
        return imageRepository.saveAndFlush(image);
    }



    @Override
    public Boolean delete(String id) {
        imageRepository.delete(findOneById(id));
        return true;
    }



    public Image findOneById(String id) {
        return  imageRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
