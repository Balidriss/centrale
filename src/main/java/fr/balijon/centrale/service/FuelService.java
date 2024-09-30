package fr.balijon.centrale.service;


import fr.balijon.centrale.entity.Fuel;
import fr.balijon.centrale.repository.FuelRepository;
import fr.balijon.centrale.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FuelService implements ServiceListInterface<Fuel, Long, Fuel, Fuel> {

    public FuelRepository fuelRepository;

    @Override
    public List<Fuel> list() {
        return fuelRepository.findAll();
    }

    @Override
    public Fuel create(Fuel o) {
        Fuel fuel = new Fuel();
        fuel.setType(o.getType());
        fuel.setLogo(o.getLogo());
        fuel.setListings(o.getListings());
        return fuelRepository.saveAndFlush(fuel);
    }

    @Override
    public Fuel update(Fuel o, Long id) {
        Fuel fuel = fuelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        fuel.setType(o.getType());
        fuel.setLogo(o.getLogo());
        fuel.setListings(o.getListings());
        return fuelRepository.saveAndFlush(fuel);
    }

    @Override
    public Boolean delete(Long id) {
        fuelRepository.delete(findOneById(id));
        return true;
    }


    @Override
    public Fuel findOneById(Long id) {
        return  fuelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
