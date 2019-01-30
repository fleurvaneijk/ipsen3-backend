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
    private DilemmaOptionsDAO dilemmaOptionsDAO;
    private RatingDAO ratingDAO;


    private List<Dilemma> dilemmaList = new ArrayList<Dilemma>();
    private List<DilemmaSubjectAdmin> dilemmaSubjectAdminList = new ArrayList<DilemmaSubjectAdmin>();
    private List<DilemmaOptions> dilemmaOptionList = new ArrayList<DilemmaOptions>();
    private List<Rating> dilemmaRatingList = new ArrayList<Rating>();

    @Inject
    public DilemmaAdminAsObjectsDAO(DilemmaDAO dilemmaDAO, DilemmaSubjectDAO dilemmaSubjectDAO,
                                    DilemmaOptionsDAO dilemmaOptionsDAO, RatingDAO ratingDAO) {
        this.setDatabase();
        this.dilemmaDAO = dilemmaDAO;
        this.dilemmaSubjectDAO = dilemmaSubjectDAO;
        this.dilemmaOptionsDAO = dilemmaOptionsDAO;
        this.ratingDAO = ratingDAO;
        dilemmaSubjectDAO.setDatabase(database);
        dilemmaOptionsDAO.setDatabase(database);
    }

    public void addDilemma(Dilemma dilemma) {
        dilemmaDAO.insert(dilemma);
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
