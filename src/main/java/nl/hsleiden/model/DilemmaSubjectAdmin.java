package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;
/**
 * This model is for the dilemma_subject information from the database
 *
 * @Author Yme Brugts
 */
public class DilemmaSubjectAdmin {

    @JsonView(View.Public.class)
    private String subject;

    @JsonView(View.Public.class)
    private String link;


    public DilemmaSubjectAdmin(String subject, String link) {
        this.subject = subject;
        this.link = link;

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(int id) {
        this.subject = subject;
    }

    public String getLink() { return link; }

    public void setLink(String Link) {
        this.link = link;
    }
}

