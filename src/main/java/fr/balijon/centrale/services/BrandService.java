package fr.balijon.centrale.services;


import fr.balijon.centrale.entity.Brand;
import fr.balijon.centrale.repository.BrandRepository;
import fr.balijon.centrale.services.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandService implements ServiceListInterface<Brand, Long, Brand, Brand> {

    public BrandRepository brandRepository;

    @Override
    public List<Brand> list() {
        return brandRepository.findAll();
    }

    @Override
    public Brand create(Brand o) {
        Brand brand = new Brand();
        brand.setName(o.getName());
        brand.setModels(o.getModels());
        return brandRepository.saveAndFlush(brand);
    }

    @Override
    public Brand update(Brand o, Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        brand.setName(o.getName());
        brand.setModels(o.getModels());
        return brandRepository.saveAndFlush(brand);
    }

    @Override
    public Boolean delete(Long id) {
        brandRepository.delete(findOneById(id));
        return true;
    }


    @Override
    public Brand findOneById(Long id) {
        return  brandRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
