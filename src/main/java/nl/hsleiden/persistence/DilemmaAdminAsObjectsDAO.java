package nl.hsleiden.persistence;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.database.Database;
import nl.hsleiden.model.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
/**
 * This DAO is to get all the lists needed for managing the dilemma page (frontend) in one object
 * This object will be converted into the DilemmaAdmin model before sending to the frontend
 *
 * @Author Yme Brugts
 */
@Singleton
public class DilemmaAdminAsObjectsDAO {



    private Database database;
    private DilemmaDAO dilemmaDAO;
    private DilemmaSubjectDAO dilemmaSubjectDAO;
    private DilemmaOptionDAO dilemmaOptionsDAO;
    private RatingDAO ratingDAO;


    private List<Dilemma> dilemmaList = new ArrayList<Dilemma>();
    private List<DilemmaSubjectAdmin> dilemmaSubjectAdminList = new ArrayList<DilemmaSubjectAdmin>();
    private List<DilemmaOption> dilemmaOptionList = new ArrayList<DilemmaOption>();
    private List<Rating> dilemmaRatingList = new ArrayList<Rating>();

    @Inject
    public DilemmaAdminAsObjectsDAO(DilemmaDAO dilemmaDAO, DilemmaSubjectDAO dilemmaSubjectDAO,
                                    DilemmaOptionDAO dilemmaOptionsDAO, RatingDAO ratingDAO) {
        this.setDatabase();
        this.dilemmaDAO = dilemmaDAO;
        this.dilemmaSubjectDAO = dilemmaSubjectDAO;
        this.dilemmaOptionsDAO = dilemmaOptionsDAO;
        this.ratingDAO = ratingDAO;
        dilemmaSubjectDAO.setDatabase(database);
        dilemmaOptionsDAO.setDatabase(database);
    }

    public int addDilemma(Dilemma dilemma) {
        return dilemmaDAO.insert(dilemma);
    }

    public void deleteDilemma(int dilemmaId) {
        dilemmaDAO.delete(dilemmaId);
    }

    public void addDilemmaOptions(DilemmaOption dilemmaOption1, DilemmaOption dilemmaOption2) {
        dilemmaOptionsDAO.addDilemmaOption(dilemmaOption1);
        dilemmaOptionsDAO.addDilemmaOption(dilemmaOption2);
    }

    public void addSubjectIfNotFound(DilemmaSubjectAdmin subject) {
        dilemmaSubjectDAO.addSubject(subject);
    }

    public DilemmaAdminAsObjects getDilemmaAdminAsObject() {
        DilemmaAdminAsObjects dilemmaAdminAsObjects = new DilemmaAdminAsObjects();


        dilemmaAdminAsObjects.setDilemmaOptionsList(dilemmaOptionsDAO.getAll());
        dilemmaAdminAsObjects.setRatingList(ratingDAO.getAll());
        dilemmaAdminAsObjects.setDilemmaList(dilemmaDAO.getAll());
        dilemmaAdminAsObjects.setDilemmaSubjectAdminList(dilemmaSubjectDAO.getAllSubjectsAdmin());

        return dilemmaAdminAsObjects;
    }

    public void addDilemmaAdminAsAttributes(DilemmaAdminAsAttributes dilemma) {

    }



    public void setDatabase() {
        this.database = ApiApplication.getDatabase();
    }
}
