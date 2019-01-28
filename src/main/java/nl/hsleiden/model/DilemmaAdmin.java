package nl.hsleiden.model;
/**
 * This Model is for managing dilemma's in the DilemmaAdminPage
 *
 * @Author Yme Brugts
 */
public class DilemmaAdmin {

    int dilemmaNumber;
    boolean pregnant;
    int weeknumber;
    String text_left;
    String text_right;
    String picture_left;
    String picture_right;
    String theme;
    String link;

    public DilemmaAdmin(int dilemmaNumber,
                        boolean pregnant,
                        int weeknumber,
                        String text_left,
                        String text_right,
                        String picture_left,
                        String picture_right,
                        String theme,
                        String link) {
        this.dilemmaNumber = dilemmaNumber;
        this.pregnant = pregnant;
        this.weeknumber = weeknumber;
        this.text_left = text_left;
        this.text_right = text_right;
        this.picture_left = picture_left;
        this.picture_right = picture_right;
        this.theme = theme;
        this.link = link;
    }

    public DilemmaAdmin() {

    }

    public int getDilemmaNumber() {
        return dilemmaNumber;
    }

    public void setDilemmaNumber(int dilemmaNumber) {
        this.dilemmaNumber = dilemmaNumber;
    }

    public boolean isPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public int getWeeknumber() {
        return weeknumber;
    }

    public void setWeeknumber(int weeknumber) {
        this.weeknumber = weeknumber;
    }

    public String getText_left() {
        return text_left;
    }

    public void setText_left(String text_left) {
        this.text_left = text_left;
    }

    public String getText_right() {
        return text_right;
    }

    public void setText_right(String text_right) {
        this.text_right = text_right;
    }

    public String getPicture_left() {
        return picture_left;
    }

    public void setPicture_left(String picture_left) {
        this.picture_left = picture_left;
    }

    public String getPicture_right() {
        return picture_right;
    }

    public void setPicture_right(String picture_right) {
        this.picture_right = picture_right;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
