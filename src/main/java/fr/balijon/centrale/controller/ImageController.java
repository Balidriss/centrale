package fr.balijon.centrale.controller;

import fr.balijon.centrale.entity.Image;

import fr.balijon.centrale.entity.dto.ImageDTO;
import fr.balijon.centrale.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/image", name = "app_image_")
public class ImageController {

    private ImageService imageService;

    @PostMapping(name = "create")
    public Image create(@RequestBody ImageDTO image)
    {
        return imageService.create(image);
    }
    @DeleteMapping(value = "/{uuid}", name = "delete")
    public Boolean delete(@PathVariable String uuid) {
        return imageService.delete(uuid);
    }

}
