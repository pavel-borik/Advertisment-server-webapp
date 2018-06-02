package cz.uhk.ppro.inzeraty.service;

import cz.uhk.ppro.inzeraty.dto.AdvertDto;
import cz.uhk.ppro.inzeraty.model.*;
import cz.uhk.ppro.inzeraty.repository.AdvertRepository;
import cz.uhk.ppro.inzeraty.repository.CategoryRepository;
import cz.uhk.ppro.inzeraty.repository.CommentRepository;
import cz.uhk.ppro.inzeraty.repository.ImageRepository;
import cz.uhk.ppro.inzeraty.util.ImageDownscaler;
import cz.uhk.ppro.inzeraty.util.ImagePersistor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdvertService {

    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private AdvertRepository adRepo;
    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private ImageRepository imageRepo;
    @Autowired
    private ImagePersistor imagePersistor;

    public AdvertService() {
    }

    @Transactional
    public void saveAdvert(AdvertDto advertDto, User user) {
        Advert advert = new Advert();
        List<MultipartFile> files = advertDto.getMpf();

        advert.setName(advertDto.getName());
        advert.setCategory(advertDto.getCategory());
        advert.setDescription(advertDto.getDescription());
        advert.setLocation(advertDto.getLocation());
        advert.setPrice(advertDto.getPrice());
        advert.setTimestamp(new Timestamp(System.currentTimeMillis()));
        advert.setUser(user);
        adRepo.save(advert);

        for(MultipartFile f:files) {
            String imgUUID = UUID.randomUUID().toString();
            imagePersistor.saveImage(f, imgUUID);
            AdvertImage a = new AdvertImage();
            a.setUuid(imgUUID);
            a.setAdvert(advert);
            imageRepo.save(a);
        }

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
        if(advert.isPresent()) {
            List<AdvertImage> advertImages = advert.get().getImages();
            File f;
            for(AdvertImage ai : advertImages) {
                try {
                    f =new File("M:/Documents/Projects/Java/Advertisment-server-webapp/src/main/webapp/resources/images/original/"+ ai.getUuid() +".jpg");
                    Files.delete(f.toPath());

                    f = new File("M:/Documents/Projects/Java/Advertisment-server-webapp/src/main/webapp/resources/images/downscaled/"+ ai.getUuid() +".jpg");
                    Files.delete(f.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            adRepo.remove(advert.get());
        }
    }

    @Transactional
    public List findAdvertsInCategory(int categoryId) {
        return adRepo.findAdvertsInCategory(categoryId);
    }
}
