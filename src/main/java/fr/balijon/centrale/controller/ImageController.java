package fr.balijon.centrale.controller;

import fr.balijon.centrale.entity.Image;

import fr.balijon.centrale.services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/image", name = "app_image_")
public class ImageController {

    private ImageService imageService;

    @GetMapping(name = "list")
    public List<Image> list()
    {
        return imageService.list();
    }

    @GetMapping(value = "/{uuid}", name = "show")
    public Image show(@PathVariable String uuid)
    {
        return imageService.findOneById(uuid);
    }


    @PostMapping(name = "create")
    public Image create(@RequestBody Image image)
    {
        return imageService.create(image);
    }

    @PutMapping(value = "/{id}", name = "update")
    public Image update(@PathVariable String id, @RequestBody Image image)
    {
        return imageService.update(image, id);
    }
}
