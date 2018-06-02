package cz.uhk.ppro.inzeraty.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "Advertimages", schema = "public")
public class AdvertImage {

    @Id
    @GeneratedValue
    private int id;

    @NotEmpty
    private String uuid;

    @ManyToOne
    private Advert advert;

    public AdvertImage() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdvertImage)) return false;

        AdvertImage that = (AdvertImage) o;

        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        return advert != null ? advert.equals(that.advert) : that.advert == null;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (advert != null ? advert.hashCode() : 0);
        return result;
    }
}
