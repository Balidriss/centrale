package fr.balijon.centrale.controller;

import fr.balijon.centrale.entity.Model;
import fr.balijon.centrale.services.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/model", name = "app_model_")
public class ModelController {

    private ModelService modelService;

    @GetMapping(name = "list")
    public List<Model> list()
    {
        return modelService.list();
    }

    @GetMapping(value = "/{id}", name = "show")
    public Model show(@PathVariable Long id)
    {
        return modelService.findOneById(id);
    }


    @PostMapping(name = "create")
    public Model create(@RequestBody Model model)
    {
        return modelService.create(model);
    }

    @PutMapping(value = "/{id}", name = "update")
    public Model update(@PathVariable Long id, @RequestBody Model model)
    {
        return modelService.update(model, id);
    }
}
