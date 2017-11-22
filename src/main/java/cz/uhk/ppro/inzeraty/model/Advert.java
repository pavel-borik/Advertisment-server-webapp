package cz.uhk.ppro.inzeraty.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "Adverts", schema = "inzeraty")
public class Advert {
    @Id
    @GeneratedValue
    private int id;
    @NotEmpty
    private String name;
    private byte[] image;
    @NotEmpty
    private String description;
    @NotNull
    private Timestamp timestamp;
    private String location;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "advert")
    private List<Comment> comments;


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



    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advert advert = (Advert) o;

        if (id != advert.id) return false;
        if (name != null ? !name.equals(advert.name) : advert.name != null) return false;
        if (!Arrays.equals(image, advert.image)) return false;
        if (description != null ? !description.equals(advert.description) : advert.description != null) return false;
        if (timestamp != null ? !timestamp.equals(advert.timestamp) : advert.timestamp != null) return false;
        if (location != null ? !location.equals(advert.location) : advert.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(image);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
