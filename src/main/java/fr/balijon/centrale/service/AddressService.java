package fr.balijon.centrale.service;


import fr.balijon.centrale.entity.Address;
import fr.balijon.centrale.entity.Model;
import fr.balijon.centrale.entity.dto.AddressDTO;
import fr.balijon.centrale.exception.entity.EntityException;
import fr.balijon.centrale.repository.AddressRepository;
import fr.balijon.centrale.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService implements ServiceListInterface<Address, Long, AddressDTO, AddressDTO> {

    public AddressRepository addressRepository;

    @Override
    public List<Address> list() {
        return addressRepository.findAll();
    }

    @Override
    public Address create(AddressDTO o) {
        Address address = new Address();
        address.setCity(o.getCity());
        address.setLatitude(o.getLatitude());
        address.setLongitude(o.getLongitude());
        address.setStreetName(o.getStreetName());
        address.setStreetNumber(o.getStreetNumber());
        address.setZipCode(o.getZipCode());
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public Address update(AddressDTO o, Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new EntityException("Address n'est pas trouvé avec id : " + id,o));
        address.setCity(o.getCity());
        address.setLatitude(o.getLatitude());
        address.setLongitude(o.getLongitude());
        address.setStreetName(o.getStreetName());
        address.setStreetNumber(o.getStreetNumber());
        address.setZipCode(o.getZipCode());
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public Boolean delete(Long id) {
        Address address = addressRepository.findById(id).orElseThrow( () -> new EntityException("Address n'est pas trouvé avec id : " + id));
        address.setUser(null);
        address.setCity(null);
        address.setListings(null);
        address.setLatitude(null);
        address.setZipCode(null);
        address.setLongitude(null);
        address.setStreetName(null);
        return true;
    }


    @Override
    public Address findOneById(Long id) {
        return  addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
