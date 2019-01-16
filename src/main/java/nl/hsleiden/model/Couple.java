package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Couple {
    @NotEmpty
    @Email
    @JsonView(View.Public.class)
    private String parentMail1;

    @NotEmpty
    @Email
    @JsonView(View.Public.class)
    private String parentMail2;

    @JsonView(View.Public.class)
    private boolean pregnant;

    @JsonView(View.Public.class)
    private int weeksPregnant;

    @JsonView(View.Public.class)
    private int lastAnswerWeekNo;

    public Couple(String parentMail1, String parentMail2, boolean pregnant, int weeksPregnant, int lastAnswerWeekNo) {
        this.parentMail1 = parentMail1;
        this.parentMail2 = parentMail2;
        this.pregnant = pregnant;
        this.weeksPregnant = weeksPregnant;
        this.lastAnswerWeekNo = lastAnswerWeekNo;
    }

    public Couple(){ }

    public String getParentMail1() {
        return parentMail1;
    }

    public void setParentMail1(String parentMail1) {
        this.parentMail1 = parentMail1;
    }

    public String getParentMail2() {
        return parentMail2;
    }

    public void setParentMail2(String parentMail2) {
        this.parentMail2 = parentMail2;
    }

    public boolean getPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public int getWeeksPregnant() {
        return weeksPregnant;
    }

    public void setWeeksPregnant(int weeksPregnant) {
        this.weeksPregnant = weeksPregnant;
    }

    public int getLastAnswerWeekNo() {
        return lastAnswerWeekNo;
    }

    public void setLastAnswerWeekNo(int lastAnswerWeekNo) {
        this.lastAnswerWeekNo = lastAnswerWeekNo;
    }
}
