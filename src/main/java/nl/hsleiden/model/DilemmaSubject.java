package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;

public class DilemmaSubject {

    @JsonView(View.Public.class)
    private String subject;

    @JsonView(View.Public.class)
    private String link;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getNumber_clicks() {
        return number_clicks;
    }

    public void setNumber_clicks(int number_clicks) {
        this.number_clicks = number_clicks;
    }

    @JsonView(View.Public.class)
    private int number_clicks;
}
