package fr.balijon.centrale.services;


import fr.balijon.centrale.entity.Image;
import fr.balijon.centrale.entity.Image;
import fr.balijon.centrale.repository.ImageRepository;
import fr.balijon.centrale.services.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageService implements ServiceListInterface<Image, String, Image, Image> {

    public ImageRepository imageRepository;

    @Override
    public List<Image> list() {
        return imageRepository.findAll();
    }

    @Override
    public Image create(Image o) {
        Image image = new Image();
        image.setPath(o.getPath());
        image.setListing(o.getListing());
        return imageRepository.saveAndFlush(image);
    }

    @Override
    public Image update(Image o, String id) {
        Image image = imageRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        image.setPath(o.getPath());
        image.setListing(o.getListing());
        return imageRepository.saveAndFlush(image);
    }

    @Override
    public void delete(String id) {
        imageRepository.delete(findOneById(id));
    }


    @Override
    public Image findOneById(String id) {
        return  imageRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
