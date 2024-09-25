package fr.balijon.centrale.services;


import fr.balijon.centrale.entity.Favorite;
import fr.balijon.centrale.repository.FavoriteRepository;
import fr.balijon.centrale.services.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FavoriteService implements ServiceListInterface<Favorite, Long, Favorite, Favorite> {

    public FavoriteRepository favoriteRepository;

    @Override
    public List<Favorite> list() {
        return favoriteRepository.findAll();
    }

    @Override
    public Favorite create(Favorite o) {
        Favorite favorite = new Favorite();
    //TODO
        return favoriteRepository.saveAndFlush(favorite);
    }

    @Override
    public Favorite update(Favorite o, Long id) {
        Favorite favorite = favoriteRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    //TODO
        return favoriteRepository.saveAndFlush(favorite);
    }

    @Override
    public void delete(Long id) {
        favoriteRepository.delete(findOneById(id));
    }


    @Override
    public Favorite findOneById(Long id) {
        return  favoriteRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
