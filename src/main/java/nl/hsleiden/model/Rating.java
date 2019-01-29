package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;

import javax.inject.Singleton;
import javax.persistence.*;
/**
 * This model is for the Rating information from the database
 *
 * @Author Yme Brugts
 */

@Entity
@Table(
        name = "rating"
)
@NamedQueries({
        @NamedQuery(name = "Rating.findAll",
                query = "select e from Rating e")
})
public class Rating {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    int id;

    @Column(name = "parent_email")
    @JsonView(View.Public.class)
    String email;

    @Column(name = "dilemma_id")
    @JsonView(View.Public.class)
    int dilemmaId;

    @Column(name = "rating_time")
    @JsonView(View.Public.class)
    int ratingTime;

    @Column(name = "rating_dilemma")
    @JsonView(View.Public.class)
    int ratingDilemma;

    public Rating(int id, int dilemmaId, int ratingTime, int ratingDilemma) {
        this.id = id;
        this.dilemmaId = dilemmaId;
        this.ratingTime = ratingTime;
        this.ratingDilemma = ratingDilemma;
    }

    public Rating() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getDilemmaId() {
        return dilemmaId;
    }

    public void setDilemmaId(int dilemmaId) {
        this.dilemmaId = dilemmaId;
    }

    public int getRatingTime() {
        return ratingTime;
    }

    public void setRatingTime(int ratingTime) {
        this.ratingTime = ratingTime;
    }

    public int getRatingDilemma() {
        return ratingDilemma;
    }

    public void setRatingDilemma(int ratingDilemma) {
        this.ratingDilemma = ratingDilemma;
    }
}
