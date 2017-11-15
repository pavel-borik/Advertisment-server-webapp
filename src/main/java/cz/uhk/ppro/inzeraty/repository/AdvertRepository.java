package cz.uhk.ppro.inzeraty.repository;

import cz.uhk.ppro.inzeraty.model.Advert;

import java.util.List;

public interface AdvertRepository {
    List<Advert> findAll();
    void save(Advert advert);
}
