package cz.uhk.ppro.inzeraty.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;

@Entity
public class Advert {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private byte[] image;
    private String description;
    private Timestamp timestamp;
    private String location;


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
