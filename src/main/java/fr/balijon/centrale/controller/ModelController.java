package fr.balijon.centrale.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.balijon.centrale.entity.Model;
import fr.balijon.centrale.entity.dto.ModelDTO;
import fr.balijon.centrale.entity.dto.ModelUpdateDTO;
import fr.balijon.centrale.jsonviews.JsonViews;
import fr.balijon.centrale.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/model", name = "app_model_")
public class ModelController {

    private ModelService modelService;

    @GetMapping(name = "list")
    @JsonView(JsonViews.ModelListView.class)
    public List<Model> list()
    {
        return modelService.list();
    }

    @GetMapping(value = "/{id}", name = "show")
    @JsonView(JsonViews.ModelListView.class)
    public Model show(@PathVariable Long id)
    {
        return modelService.findOneById(id);
    }


    @PostMapping(name = "create")
    public Model create(@RequestBody ModelDTO model)
    {
        return modelService.create(model);
    }

    @PutMapping(value = "/{id}", name = "update")
    public Model update(@PathVariable Long id, @RequestBody ModelUpdateDTO model)
    {
        return modelService.update(model, id);
    }

    @DeleteMapping(value = "/{id}", name = "delete")
    public Boolean delete(@PathVariable Long id) {
        return modelService.delete(id);
    }
}
