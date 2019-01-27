package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;

public class DilemmaOptions {

    @JsonView(View.Public.class)
    private int id;

    @JsonView(View.Public.class)
    private String imagePath;

    @JsonView(View.Public.class)
    private String text;

    @JsonView(View.Public.class)
    private int dilemmaId;

    public DilemmaOptions(int id, String imagePath, String text, int dilemmaId) {
        this.id = id;
        this.imagePath = imagePath;
        this.text = text;
        this.dilemmaId = dilemmaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDilemmaId() {
        return dilemmaId;
    }

    public void setDilemmaId(int dilemmaId) {
        this.dilemmaId = dilemmaId;
    }
}
