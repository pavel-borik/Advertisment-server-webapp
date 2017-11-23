package cz.uhk.ppro.inzeraty.repository.jpa;

import cz.uhk.ppro.inzeraty.model.Category;
import cz.uhk.ppro.inzeraty.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaCategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Category> findAll() {
        return em.createQuery("SELECT c from Category c").getResultList();
    }

    @Override
    public Category findByName(String name) {
        Query query = this.em.createQuery("SELECT c from Category c where c.name =:name");
        query.setParameter("name", name);
        return (Category) query.getSingleResult();
    }


}
