package cz.uhk.ppro.inzeraty.service;

import cz.uhk.ppro.inzeraty.dto.AdvertDto;
import cz.uhk.ppro.inzeraty.model.*;
import cz.uhk.ppro.inzeraty.repository.AdvertRepository;
import cz.uhk.ppro.inzeraty.repository.CategoryRepository;
import cz.uhk.ppro.inzeraty.repository.CommentRepository;
import cz.uhk.ppro.inzeraty.util.ImageDownscaler;
import cz.uhk.ppro.inzeraty.util.ImagePersistor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public void saveAdvert(AdvertDto advertDto, User user) {
        Advert advert = new Advert();
        List<AdvertImage> advertImages = new ArrayList<>();
        List<MultipartFile> files = advertDto.getMpf();

        for(MultipartFile f:files) {
            String imgUUID = UUID.randomUUID().toString();
            ImagePersistor.saveImage(f, imgUUID);
            AdvertImage a = new AdvertImage();
            a.setUuid(imgUUID);
            a.setAdvert(advert);
            //TODO save image
        }

        advert.setName(advertDto.getName());
        advert.setCategory(advertDto.getCategory());
        advert.setDescription(advertDto.getDescription());
        advert.setLocation(advertDto.getLocation());
        advert.setPrice(advertDto.getPrice());
        advert.setImages(advertImages);
        advert.setTimestamp(new Timestamp(System.currentTimeMillis()));
        advert.setUser(user);
        adRepo.save(advert);
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

    @Transactional
    public void removeAdvert(int advertId) {
        Optional<Advert> advert = adRepo.findById(advertId);
        if(advert.isPresent()) adRepo.remove(advert.get());
    }

    @Transactional
    public List findAdvertsInCategory(int categoryId) {
        return adRepo.findAdvertsInCategory(categoryId);
    }
}
