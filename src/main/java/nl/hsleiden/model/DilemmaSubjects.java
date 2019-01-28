package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;
/**
 * This model is for the dilemma_subject information from the database
 *
 * @Author Yme Brugts
 */
public class DilemmaSubjects {

    @JsonView(View.Public.class)
    private String subject;

    @JsonView(View.Public.class)
    private String link;

    @JsonView(View.Public.class)
    private int number_clicks;

    public DilemmaSubjects(String subject, String link, int number_clicks) {
        this.subject = subject;
        this.link = link;
        this.number_clicks = number_clicks;
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

    public int getNumber_clicks() {
        return number_clicks;
    }

    public void setNumber_clicks(int number_clicks) {
        this.number_clicks = number_clicks;
    }
}

