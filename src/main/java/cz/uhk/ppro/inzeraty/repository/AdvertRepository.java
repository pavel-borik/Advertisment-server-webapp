package cz.uhk.ppro.inzeraty.repository;

import cz.uhk.ppro.inzeraty.model.Advert;

import java.util.List;
import java.util.Optional;

public interface AdvertRepository {
    Optional<Advert> findById(int id);
    List<Advert> findAll();
    List<Advert> findAdvertsInCategory(int categoryId);
    void save(Advert advert);
    void remove(Advert advert);
}
