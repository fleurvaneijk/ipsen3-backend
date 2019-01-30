package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;

import javax.persistence.*;
/**
 * This Model works based on the Hibernate Framework.
 *
 * @Author Yme Brugts
 */
@Entity
@Table(
        name = "dilemma",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"pregnant", "week_no"})}
)
@NamedNativeQueries({
        @NamedNativeQuery(name = "Dilemma.getByWeekAndPregnant",
                query = "select * FROM Dilemma WHERE week_no = :id AND pregnant = :pregnant",
                resultClass = Dilemma.class
        )
})
public class Dilemma {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    private Integer dilemmaId;

    @Column(name = "subject", length = 30)
    @JsonView(View.Public.class)
    private String subject;

    @Column(name = "pregnant")
    @JsonView(View.Public.class)
    private boolean pregnant;

    @Column(name = "week_no")
    @JsonView(View.Public.class)
    private int weekNr;

    public Dilemma(){}

    public Dilemma(Integer dilemmaId, String subject, boolean pregnant, int weekNr) {
        this.dilemmaId = dilemmaId;
        this.subject = subject;
        this.pregnant = pregnant;
        this.weekNr = weekNr;
    }

    public Integer getDilemmaId() {
        return dilemmaId;
    }

    public void setDilemmaId(Integer dilemmaId) {
        this.dilemmaId = dilemmaId;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public int getWeekNr() {
        return weekNr;
    }

    public void setWeekNr(int weekNr) {
        this.weekNr = weekNr;
    }
}
