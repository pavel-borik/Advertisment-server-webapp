package cz.uhk.ppro.inzeraty.service;

import cz.uhk.ppro.inzeraty.model.Rating;
import cz.uhk.ppro.inzeraty.model.User;
import cz.uhk.ppro.inzeraty.repository.RatingRepository;
import cz.uhk.ppro.inzeraty.repository.RoleRepository;
import cz.uhk.ppro.inzeraty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepo;
    private RoleRepository roleRepo;
    private RatingRepository ratingRepo;

    @Autowired
    public UserService(UserRepository userRepo, RoleRepository roleRepo, RatingRepository ratingRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.ratingRepo = ratingRepo;
    }

    @Transactional
    public void save(User user) throws DataAccessException {
        Optional<User> u = userRepo.findByUsername(user.getUsername());
        if(!u.isPresent()) {
            user.setCreationTime(new Timestamp(System.currentTimeMillis()));
            user.setRole(roleRepo.findByName("USER").get());
            userRepo.save(user);
        }
    }

    @Transactional
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Transactional
    public Optional<User> findById(int id) {
        return userRepo.findById(id);
    }

    @Transactional
    public void saveRating(Rating rating, User author, int ratedUserId) {
        rating.setAuthor(author);
        rating.setRatedUser(this.findById(ratedUserId).get());
        rating.setPostDate(new Timestamp(System.currentTimeMillis()));
        ratingRepo.save(rating);
    }


}
