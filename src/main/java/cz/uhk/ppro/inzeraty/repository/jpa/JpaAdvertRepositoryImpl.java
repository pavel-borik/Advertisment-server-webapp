package cz.uhk.ppro.inzeraty.repository.jpa;

import cz.uhk.ppro.inzeraty.model.Advert;
import cz.uhk.ppro.inzeraty.repository.AdvertRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class JpaAdvertRepositoryImpl implements AdvertRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Advert> findAll() {
        return em.createQuery("select ad from Advert ad").getResultList();
    }

    @Override
    @Transactional
    public void save(Advert advert) {
        em.persist(advert);
    }
}
