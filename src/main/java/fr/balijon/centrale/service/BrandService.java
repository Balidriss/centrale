package fr.balijon.centrale.service;


import fr.balijon.centrale.entity.Brand;
import fr.balijon.centrale.entity.Model;
import fr.balijon.centrale.entity.dto.BrandDTO;
import fr.balijon.centrale.exception.entity.EntityException;
import fr.balijon.centrale.repository.BrandRepository;
import fr.balijon.centrale.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BrandService implements ServiceListInterface<Brand, Long, BrandDTO, BrandDTO> {

    public BrandRepository brandRepository;

    @Override
    public List<Brand> list() {
        return brandRepository.findAll();
    }

    @Override
    public Brand create(BrandDTO o) {
        Brand brand = new Brand();
        brand.setName(o.getName());
        return brandRepository.saveAndFlush(brand);
    }

    @Override
    public Brand update(BrandDTO o, Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new EntityException("Pas trouvé le Brand", o));
        brand.setName(o.getName());
        return brandRepository.saveAndFlush(brand);
    }

    @Override
    public Boolean delete(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new EntityException("Brand n'est pas trouvé avec id : " + id));
        brand.setName("Cette marque n'éxiste plus");
        brandRepository.save(brand);
        return true;

    }


    @Override
    public Brand findOneById(Long id) {
        return brandRepository.findById(id).orElseThrow(() -> new EntityException("Brand n'est pas trouvé avec id : " + id));
    }
}
