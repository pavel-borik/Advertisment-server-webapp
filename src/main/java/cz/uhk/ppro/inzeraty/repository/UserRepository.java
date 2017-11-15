package cz.uhk.ppro.inzeraty.repository;

import cz.uhk.ppro.inzeraty.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(int id);
    void save(User user);
}
