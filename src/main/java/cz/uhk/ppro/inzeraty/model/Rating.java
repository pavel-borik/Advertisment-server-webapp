package cz.uhk.ppro.inzeraty.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Ratings", schema = "public")
public class Rating {
    @Id
    @GeneratedValue
    private int id;

    @NotEmpty
    private String ratingText;

    private Timestamp postDate;

    @ManyToOne
    private User author;

    @ManyToOne
    private User ratedUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRatingText() {
        return ratingText;
    }

    public void setRatingText(String rating) {
        this.ratingText = rating;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getRatedUser() {
        return ratedUser;
    }

    public void setRatedUser(User ratedUser) {
        this.ratedUser = ratedUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating1 = (Rating) o;

        if (id != rating1.id) return false;
        if (ratingText != null ? !ratingText.equals(rating1.ratingText) : rating1.ratingText != null) return false;
        if (postDate != null ? !postDate.equals(rating1.postDate) : rating1.postDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ratingText != null ? ratingText.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        return result;
    }
}
