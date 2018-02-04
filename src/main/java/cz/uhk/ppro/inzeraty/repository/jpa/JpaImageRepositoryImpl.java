package cz.uhk.ppro.inzeraty.repository.jpa;

import cz.uhk.ppro.inzeraty.model.AdvertImage;
import cz.uhk.ppro.inzeraty.repository.ImageRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaImageRepositoryImpl implements ImageRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(AdvertImage image) {
        em.persist(image);
    }
}
