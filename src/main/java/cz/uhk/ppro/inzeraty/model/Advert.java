package cz.uhk.ppro.inzeraty.model;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Entity
@Indexed
@Table(name = "Adverts", schema = "public")
public class Advert {
    @Id
    @GeneratedValue
    private int id;
    @Field(index = Index.YES, analyze = Analyze.YES,store = Store.NO)
    @NotEmpty
    private String name;
    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String description;
    @NotNull
    private Timestamp timestamp;
    @NotEmpty
    private String location;
    @NotEmpty
    private String price;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.REMOVE)
    private List<AdvertImage> images;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<AdvertImage> getImages() {
        return images;
    }

    public void setImages(List<AdvertImage> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advert)) return false;

        Advert advert = (Advert) o;

        if (id != advert.id) return false;
        if (name != null ? !name.equals(advert.name) : advert.name != null) return false;
        if (description != null ? !description.equals(advert.description) : advert.description != null) return false;
        if (timestamp != null ? !timestamp.equals(advert.timestamp) : advert.timestamp != null) return false;
        if (location != null ? !location.equals(advert.location) : advert.location != null) return false;
        if (price != null ? !price.equals(advert.price) : advert.price != null) return false;
        if (category != null ? !category.equals(advert.category) : advert.category != null) return false;
        if (user != null ? !user.equals(advert.user) : advert.user != null) return false;
        if (comments != null ? !comments.equals(advert.comments) : advert.comments != null) return false;
        return images != null ? images.equals(advert.images) : advert.images == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        return result;
    }
}
