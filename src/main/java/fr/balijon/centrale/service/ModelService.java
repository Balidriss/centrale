package fr.balijon.centrale.service;


import fr.balijon.centrale.entity.Model;
import fr.balijon.centrale.repository.ModelRepository;
import fr.balijon.centrale.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelService implements ServiceListInterface<Model, Long, Model, Model> {

    public ModelRepository modelRepository;

    @Override
    public List<Model> list() {
        return modelRepository.findAll();
    }

    @Override
    public Model create(Model o) {
        Model model = new Model();
        model.setName(o.getName());
        model.setBrand(o.getBrand());
        model.setListings(o.getListings());
        return modelRepository.saveAndFlush(model);
    }

    @Override
    public Model update(Model o, Long id) {
        Model model = modelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        model.setName(o.getName());
        model.setBrand(o.getBrand());
        model.setListings(o.getListings());
        return modelRepository.saveAndFlush(model);
    }

    @Override
    public Boolean delete(Long id) {
        modelRepository.delete(findOneById(id));
        return true;
    }


    @Override
    public Model findOneById(Long id) {
        return  modelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
