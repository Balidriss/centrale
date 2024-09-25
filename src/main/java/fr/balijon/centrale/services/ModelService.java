package fr.balijon.centrale.services;


import fr.balijon.centrale.entity.Model;
import fr.balijon.centrale.repository.ModelRepository;
import fr.balijon.centrale.services.interfaces.ServiceListInterface;
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
    //TODO
        return modelRepository.saveAndFlush(model);
    }

    @Override
    public Model update(Model o, Long id) {
        Model model = modelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    //TODO
        return modelRepository.saveAndFlush(model);
    }

    @Override
    public void delete(Long id) {
        modelRepository.delete(findOneById(id));
    }


    @Override
    public Model findOneById(Long id) {
        return  modelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
