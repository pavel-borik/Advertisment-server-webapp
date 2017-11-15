package cz.uhk.ppro.inzeraty.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Ratings", schema = "inzeraty", catalog = "")
public class Rating {
    @Id
    @GeneratedValue
    private int id;
    private String rating;
    private Timestamp postDate;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }



    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating1 = (Rating) o;

        if (id != rating1.id) return false;
        if (rating != null ? !rating.equals(rating1.rating) : rating1.rating != null) return false;
        if (postDate != null ? !postDate.equals(rating1.postDate) : rating1.postDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        return result;
    }
}
