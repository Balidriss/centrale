package fr.balijon.centrale.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.balijon.centrale.entity.Brand;
import fr.balijon.centrale.entity.dto.BrandDTO;
import fr.balijon.centrale.entity.dto.BrandListDTO;
import fr.balijon.centrale.jsonviews.JsonViews;
import fr.balijon.centrale.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/brand", name = "app_brand_")
public class BrandController {

    private BrandService brandService;

    //@GetMapping(name = "list")
    //@JsonView(JsonViews.BrandListView.class)
    //public List<Brand> list()
    //{
    //    return brandService.list();
    //}

    @GetMapping
    public Page<BrandListDTO> list(
            @PageableDefault(
                    size = 12,
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        return brandService.list(pageable);
    }

    @GetMapping(value = "/{id}", name = "show")
    public Brand show(@PathVariable Long id)
    {
        return brandService.findOneById(id);
    }

    @PostMapping(name = "create")
    public Brand create(@RequestBody BrandDTO brand)
    {
        return brandService.create(brand);
    }

    @PutMapping(value = "/{id}", name = "update")
    public Brand update(@PathVariable Long id, @RequestBody BrandDTO brand)
    {
        return brandService.update(brand, id);
    }

    @DeleteMapping(value = "/{id}", name = "delete")
    public Boolean delete(@PathVariable Long id) {
        return brandService.delete(id);
    }
}
