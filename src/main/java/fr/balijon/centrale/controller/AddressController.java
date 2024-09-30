package fr.balijon.centrale.controller;

import fr.balijon.centrale.entity.Address;
import fr.balijon.centrale.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/address", name = "app_address_")
public class AddressController {

    private AddressService addressService;

    @GetMapping(name = "list")
    public List<Address> list()
    {
        return addressService.list();
    }

    @GetMapping(value = "/{id}", name = "show")
    public Address show(@PathVariable Long id)
    {
        return addressService.findOneById(id);
    }


    @PostMapping(name = "create")
    public Address create(@RequestBody Address address)
    {
        return addressService.create(address);
    }

    @PutMapping(value = "/{id}", name = "update")
    public Address update(@PathVariable Long id, @RequestBody Address address)
    {
        return addressService.update(address, id);
    }
}
