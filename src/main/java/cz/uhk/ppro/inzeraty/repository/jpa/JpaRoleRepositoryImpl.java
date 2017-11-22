package cz.uhk.ppro.inzeraty.repository.jpa;

import cz.uhk.ppro.inzeraty.model.Role;
import cz.uhk.ppro.inzeraty.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class JpaRoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Role> findByName(String name) {
        Query query = this.em.createQuery("SELECT r FROM Role r WHERE r.name =:name");
        query.setParameter("name", name);
        return (Optional<Role>) query.setMaxResults(1).getResultList().stream().findFirst();
    }
}
