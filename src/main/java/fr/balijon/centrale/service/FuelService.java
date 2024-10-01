package fr.balijon.centrale.service;


import fr.balijon.centrale.entity.Fuel;
import fr.balijon.centrale.entity.Listing;
import fr.balijon.centrale.entity.dto.FuelDTO;
import fr.balijon.centrale.exception.entity.EntityException;
import fr.balijon.centrale.repository.FuelRepository;
import fr.balijon.centrale.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FuelService implements ServiceListInterface<Fuel, Long, FuelDTO, FuelDTO> {

    public FuelRepository fuelRepository;

    @Override
    public List<Fuel> list() {
        return fuelRepository.findAll();
    }

    @Override
    public Fuel create(FuelDTO o) {
        Fuel fuel = new Fuel();
        fuel.setType(o.getType());
        fuel.setLogo(o.getLogo());
        return fuelRepository.saveAndFlush(fuel);
    }

    @Override
    public Fuel update(FuelDTO o, Long id) {
        Fuel fuel = fuelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        fuel.setType(o.getType());
        fuel.setLogo(o.getLogo());
        return fuelRepository.saveAndFlush(fuel);
    }

    @Override
    public Boolean delete(Long id) {
        Fuel fuel = fuelRepository.findById(id).orElseThrow(() -> new EntityException("FUel n'est pas trouvé avec id : " + id));
        fuel.setLogo(null);
        fuel.setType("fuel n'éxiste plus");
        fuel.setListings(null);
        return true;
    }


    @Override
    public Fuel findOneById(Long id) {
        return  fuelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
