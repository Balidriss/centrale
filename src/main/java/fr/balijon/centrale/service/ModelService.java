package fr.balijon.centrale.service;


import fr.balijon.centrale.entity.Brand;
import fr.balijon.centrale.entity.Model;
import fr.balijon.centrale.entity.dto.ModelDTO;
import fr.balijon.centrale.entity.dto.ModelUpdateDTO;
import fr.balijon.centrale.exception.entity.EntityException;
import fr.balijon.centrale.repository.BrandRepository;
import fr.balijon.centrale.repository.ModelRepository;
import fr.balijon.centrale.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelService implements ServiceListInterface<Model, Long, ModelDTO, ModelUpdateDTO> {

    public ModelRepository modelRepository;
    public BrandService brandService;
    public BrandRepository brandRepository;

    @Override
    public List<Model> list() {
        return modelRepository.findAll();
    }

    @Override
    public Model create(ModelDTO o) {
        Model model = new Model();
        model.setName(o.getName());
        model.setBrand(brandService.findOneById(o.getBrandId()));
        return modelRepository.saveAndFlush(model);
    }

    @Override
    public Model update(ModelUpdateDTO o, Long id) {
        Model model = modelRepository.findById(id).orElseThrow(() -> new EntityException("Model pas trouvé",  o));
        model.setName(o.getName());
        return modelRepository.saveAndFlush(model);
    }

    @Override
    public Boolean delete(Long id) {
        Model model = modelRepository.findById(id).orElseThrow( () -> new EntityException("Model n'est pas trouvé avec id : " + id));
        model.setName("Ce Modele n'éxiste plus");
//        if(model.getBrand() != null) {
//            Brand brand = model.getBrand();
//            brand.getModels().forEach(m -> m.setBrand(null));
//            brandRepository.save(brand);
//        }
        modelRepository.save(model);
        return true;
    }

    @Override
    public Model findOneById(Long id) {
        return  modelRepository.findById(id).orElseThrow(() -> new EntityException("Model n'est pas trouvé avec id : " + id));
    }
}
