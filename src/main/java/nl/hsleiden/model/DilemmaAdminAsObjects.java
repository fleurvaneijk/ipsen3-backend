package nl.hsleiden.model;

import java.util.ArrayList;
import java.util.List;
/**
 * This model is for getting all the lists for managing dilemma page in one object
 * This object will be converted into the DilemmaAdmin model before sending to the frontend
 *
 * @Author Yme Brugts
 */
public class DilemmaAdminAsObjects {

    List<Dilemma> DilemmaList;
    List<Rating> RatingList;
    List<DilemmaOptions> dilemmaOptionsList;
    List<DilemmaSubjects> dilemmaSubjectsList;

    public DilemmaAdminAsObjects(List<Dilemma> dilemmaList,
                                 List<Rating> ratingList,
                                 List<DilemmaOptions> dilemmaOptionsList,
                                 List<DilemmaSubjects> dilemmaSubjectsList) {
        DilemmaList = dilemmaList;
        RatingList = ratingList;
        this.dilemmaOptionsList = dilemmaOptionsList;
        this.dilemmaSubjectsList = dilemmaSubjectsList;
    }

    public DilemmaAdminAsObjects() {

    }

    public List<Dilemma> getDilemmaList() {
        return DilemmaList;
    }

    public void setDilemmaList(List<Dilemma> dilemmaList) {
        DilemmaList = dilemmaList;
    }

    public List<Rating> getRatingList() {
        return RatingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        RatingList = ratingList;
    }

    public List<DilemmaOptions> getDilemmaOptionsList() {
        return dilemmaOptionsList;
    }

    public void setDilemmaOptionsList(List<DilemmaOptions> dilemmaOptionsList) {
        this.dilemmaOptionsList = dilemmaOptionsList;
    }

    public List<DilemmaSubjects> getDilemmaSubjectsList() {
        return dilemmaSubjectsList;
    }

    public void setDilemmaSubjectsList(List<DilemmaSubjects> dilemmaSubjectsList) {
        this.dilemmaSubjectsList = dilemmaSubjectsList;
    }
}
