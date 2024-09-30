package fr.balijon.centrale.service;


import fr.balijon.centrale.entity.Address;
import fr.balijon.centrale.repository.AddressRepository;
import fr.balijon.centrale.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService implements ServiceListInterface<Address, Long, Address, Address> {

    public AddressRepository addressRepository;

    @Override
    public List<Address> list() {
        return addressRepository.findAll();
    }

    @Override
    public Address create(Address o) {
        Address address = new Address();
        address.setCity(o.getCity());
        address.setLatitude(o.getLatitude());
        address.setLongitude(o.getLongitude());
        address.setListings(o.getListings());
        address.setStreetName(o.getStreetName());
        address.setStreetNumber(o.getStreetNumber());
        address.setZipCode(o.getZipCode());
        address.setUser(o.getUser());
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public Address update(Address o, Long id) {
        Address address = addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        address.setCity(o.getCity());
        address.setLatitude(o.getLatitude());
        address.setLongitude(o.getLongitude());
        address.setListings(o.getListings());
        address.setStreetName(o.getStreetName());
        address.setStreetNumber(o.getStreetNumber());
        address.setZipCode(o.getZipCode());
        address.setUser(o.getUser());
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public Boolean delete(Long id) {
        addressRepository.delete(findOneById(id));
        return true;
    }


    @Override
    public Address findOneById(Long id) {
        return  addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
