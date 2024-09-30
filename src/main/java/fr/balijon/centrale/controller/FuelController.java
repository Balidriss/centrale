package fr.balijon.centrale.controller;

import fr.balijon.centrale.entity.Fuel;
import fr.balijon.centrale.service.FuelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/fuel", name = "app_fuel_")
public class FuelController {

    private FuelService fuelService;

    @GetMapping(name = "list")
    public List<Fuel> list()
    {
        return fuelService.list();
    }

    @GetMapping(value = "/{id}", name = "show")
    public Fuel show(@PathVariable Long id)
    {
        return fuelService.findOneById(id);
    }


    @PostMapping(name = "create")
    public Fuel create(@RequestBody Fuel fuel)
    {
        return fuelService.create(fuel);
    }

    @PutMapping(value = "/{id}", name = "update")
    public Fuel update(@PathVariable Long id, @RequestBody Fuel fuel)
    {
        return fuelService.update(fuel, id);
    }
}
