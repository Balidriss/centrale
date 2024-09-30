package fr.balijon.centrale.controller;

import fr.balijon.centrale.entity.Brand;
import fr.balijon.centrale.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/brand", name = "app_brand_")
public class BrandController {

    private BrandService brandService;

    @GetMapping(name = "list")
    public List<Brand> list()
    {
        return brandService.list();
    }

    @GetMapping(value = "/{id}", name = "show")
    public Brand show(@PathVariable Long id)
    {
        return brandService.findOneById(id);
    }


    @PostMapping(name = "create")
    public Brand create(@RequestBody Brand brand)
    {
        return brandService.create(brand);
    }

    @PutMapping(value = "/{id}", name = "update")
    public Brand update(@PathVariable Long id, @RequestBody Brand brand)
    {
        return brandService.update(brand, id);
    }
}
