package cz.uhk.ppro.inzeraty.service;

import cz.uhk.ppro.inzeraty.model.Advert;
import cz.uhk.ppro.inzeraty.model.Category;
import cz.uhk.ppro.inzeraty.model.Comment;
import cz.uhk.ppro.inzeraty.model.User;
import cz.uhk.ppro.inzeraty.repository.AdvertRepository;
import cz.uhk.ppro.inzeraty.repository.CategoryRepository;
import cz.uhk.ppro.inzeraty.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertService {
    private CategoryRepository categoryRepo;
    private AdvertRepository adRepo;
    private CommentRepository commentRepo;

    @Autowired
    public AdvertService(CategoryRepository categoryRepo, AdvertRepository adRepo, CommentRepository commentRepo) {
        this.categoryRepo = categoryRepo;
        this.adRepo = adRepo;
        this.commentRepo = commentRepo;
    }

    @Transactional
    public void saveAdvert(Advert ad, User user) {
        ad.setTimestamp(new Timestamp(System.currentTimeMillis()));
        ad.setUser(user);
        adRepo.save(ad);
    }

    @Transactional
    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }

    @Transactional
    public Category findCategoryByName(String name){
        return categoryRepo.findByName(name);
    }

    @Transactional()
    public List<Advert> findAdverts() throws DataAccessException {
        return adRepo.findAll();
    }
    @Transactional
    public Optional<Advert> findById(int id) {
        return adRepo.findById(id);
    }

    @Transactional
    public void saveComment(Comment comment, User user, int advertId) {
        comment.setAuthor(user);
        comment.setAdvert(adRepo.findById(advertId).get());
        comment.setPostDate(new Timestamp(System.currentTimeMillis()));
        commentRepo.save(comment);
    }
}
