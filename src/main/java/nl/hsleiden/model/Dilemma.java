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
public class Dilemma {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    private int dilemmaId;

    @Column(name = "subject", length = 30)
    @JsonView(View.Public.class)
    private String subject;

    @Column(name = "pregnant", nullable = false)
    @JsonView(View.Public.class)
    private boolean pregnant;

    @Column(name = "week_no", nullable = false)
    @JsonView(View.Public.class)
    private int weekNr;

    public Dilemma(){}

    public Dilemma(int dilemmaId, String subject, boolean pregnant, int weekNr) {
        this.dilemmaId = dilemmaId;
        this.subject = subject;
        this.pregnant = pregnant;
        this.weekNr = weekNr;
    }

    public int getDilemmaId() {
        return dilemmaId;
    }

    public void setDilemmaId(int dilemmaId) {
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
