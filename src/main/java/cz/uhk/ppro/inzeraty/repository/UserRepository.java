package cz.uhk.ppro.inzeraty.repository;

import cz.uhk.ppro.inzeraty.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(int id);
    Optional<User> findByUsername(String username);
    void save(User user);
    Collection<User> findByLastName(String lastName);
}
