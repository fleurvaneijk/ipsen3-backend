package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Child {

    private int childId;
    private int koppelId;
    private String parentMail;
    private String birthdate;

    @JsonCreator
    public Child(@JsonProperty("parentEmail") String parentMail,
                 @JsonProperty("birthdate") String birthdate) {
        this.parentMail = parentMail;
        this.birthdate = (birthdate == null) ? "01-01-01" : birthdate;
    }

    public Child(int id, int couple_id, String birthdate) {
        this.childId = id;
        this.koppelId = id;
        this.birthdate = birthdate;
    }

    public Child() { }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public int getKoppelId() {
        return koppelId;
    }

    public void setKoppelId(int koppelId) {
        this.childId = koppelId;
    }

    public String getParentMail() {
        return parentMail;
    }

    public void setParentMail(String parentMail) {
        this.parentMail = parentMail;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
