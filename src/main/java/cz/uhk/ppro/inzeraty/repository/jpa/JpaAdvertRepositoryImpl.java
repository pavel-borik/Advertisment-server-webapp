package cz.uhk.ppro.inzeraty.repository.jpa;

import cz.uhk.ppro.inzeraty.model.Advert;
import cz.uhk.ppro.inzeraty.repository.AdvertRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaAdvertRepositoryImpl implements AdvertRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Advert> findById(int id) {
        Query query = this.em.createQuery("SELECT a FROM Advert a WHERE a.id =:id");
        query.setParameter("id", id);
        return (Optional<Advert>) query.setMaxResults(1).getResultList().stream().findFirst();
    }

    @Override
    public List<Advert> findAll() {
        return em.createQuery("select ad from Advert ad order by ad.timestamp desc").getResultList();
    }

    @Override
    public List<Advert> findAdvertsInCategory(int categoryId) {
        Query query = this.em.createQuery("select ad from Advert ad where ad.category.id = :categoryId order by ad.timestamp desc");
        query.setParameter("categoryId", categoryId);
        return query.getResultList();
    }

    @Override
    public void save(Advert advert) {
        Optional<Advert> a = this.findById(advert.getId());
        if(a.isPresent()) {
            em.merge(advert);
        } else {
            em.persist(advert);
        }
    }

    @Override
    public void remove(Advert advert) {
        em.remove(advert);
    }
}
