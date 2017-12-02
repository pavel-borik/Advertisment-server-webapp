package cz.uhk.ppro.inzeraty.repository.jpa;

import cz.uhk.ppro.inzeraty.model.Rating;
import cz.uhk.ppro.inzeraty.repository.RatingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaRatingRepositoryImpl implements RatingRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Rating rating) {
        em.persist(rating);
    }
}
