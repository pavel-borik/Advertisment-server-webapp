package cz.uhk.ppro.inzeraty.repository.jpa;

import cz.uhk.ppro.inzeraty.model.User;
import cz.uhk.ppro.inzeraty.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return this.em.createQuery("SELECT usrs FROM Users usrs").getResultList();
    }

    @Override
    public User findById(int id) {
        Query query = this.em.createQuery("SELECT user FROM Users user WHERE user.id =:id");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Transactional
    @Override
    public void save(User user) {
            this.em.persist(user);
    }
}
