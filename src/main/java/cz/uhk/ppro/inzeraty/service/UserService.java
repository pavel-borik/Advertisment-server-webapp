package cz.uhk.ppro.inzeraty.service;

import cz.uhk.ppro.inzeraty.model.User;
import cz.uhk.ppro.inzeraty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    public void save(User user) throws DataAccessException {
        Optional<User> u = userRepo.findByUsername(user.getUsername());
        if(!u.isPresent()) userRepo.save(user);
    }
}
