package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;

public class Dilemma {

    @JsonView(View.Public.class)
    private int dilemmaId;

    @JsonView(View.Public.class)
    private String Subject;

    @JsonView(View.Public.class)
    private boolean pregnant;

    @JsonView(View.Public.class)
    private int weekNr;

    public Dilemma(int dilemmaId, String subject, boolean pregnant, int weekNr) {
        this.dilemmaId = dilemmaId;
        Subject = subject;
        this.pregnant = pregnant;
        this.weekNr = weekNr;
    }

    public Dilemma(){}

    public int getDilemmaId() {
        return dilemmaId;
    }

    public void setDilemmaId(int dilemmaId) {
        this.dilemmaId = dilemmaId;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
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
