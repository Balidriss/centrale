package fr.balijon.centrale.service;


import fr.balijon.centrale.entity.Brand;
import fr.balijon.centrale.entity.Listing;
import fr.balijon.centrale.entity.Model;
import fr.balijon.centrale.entity.dto.BrandDTO;
import fr.balijon.centrale.entity.dto.BrandListDTO;
import fr.balijon.centrale.exception.entity.EntityException;
import fr.balijon.centrale.repository.BrandRepository;
import fr.balijon.centrale.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BrandService  {

    public BrandRepository brandRepository;

    //@Override
    //public List<Brand> list() {
    //    return brandRepository.findAll();
    //}
    public Page<BrandListDTO> list(Pageable pageable) {
//        listingRepository.findAll(Sort.by("createdAt").descending());
        Page<Brand> brands = brandRepository.findAll(pageable);
        List<BrandListDTO> listDTOs = new ArrayList<BrandListDTO>();
        brands.forEach((item) -> {
            listDTOs.add(new BrandListDTO(
                    item.getId(),
                    item.getName(),
                    item.modelCount()
            ));
        });
        return new PageImpl<>(listDTOs, pageable, brands.getTotalElements());
    }


    public Brand create(BrandDTO o) {
        Brand brand = new Brand();
        brand.setName(o.getName());
        return brandRepository.saveAndFlush(brand);
    }


    public Brand update(BrandDTO o, Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new EntityException("Pas trouvé le Brand", o));
        brand.setName(o.getName());
        return brandRepository.saveAndFlush(brand);
    }


    public Boolean delete(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new EntityException("Brand n'est pas trouvé avec id : " + id));
        brand.setName("Cette marque n'éxiste plus");
        brandRepository.save(brand);
        return true;

    }



    public Brand findOneById(Long id) {
        return brandRepository.findById(id).orElseThrow(() -> new EntityException("Brand n'est pas trouvé avec id : " + id));
    }


}
