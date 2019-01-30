package nl.hsleiden.service;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.*;
import nl.hsleiden.persistence.DilemmaAdminAsObjectsDAO;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.security.auth.Subject;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Singleton
public class DilemmaAdminAsObjectsService {

    private final DilemmaAdminAsObjectsDAO dao;

    @Inject
    public DilemmaAdminAsObjectsService(DilemmaAdminAsObjectsDAO dao){
        this.dao = dao;
    }

    public DilemmaAdminAsObjects getAll() { return dao.getDilemmaAdminAsObject(); }

    public void AddDilemmaAdmin(DilemmaAdminAsAttributes dilemmaAdminAsAttributes) {
        DilemmaSubjectAdmin subjectDatabase = new DilemmaSubjectAdmin(
                dilemmaAdminAsAttributes.get_subject(),
                dilemmaAdminAsAttributes.get_link()
        );
        dao.addSubjectIfNotFound(subjectDatabase);


        Dilemma dilemmaDatabase = new Dilemma(
                dilemmaAdminAsAttributes.get_dilemmaId(),
                dilemmaAdminAsAttributes.get_subject(),
                dilemmaAdminAsAttributes.is_pregnant(),
                dilemmaAdminAsAttributes.get_weekNr()
        );
        Dilemma NewlyMadeDilemma = dao.addDilemma(dilemmaDatabase);

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DilemmaOptions dilemmaOptionsDatabase1 = new DilemmaOptions(
                1,
                dilemmaAdminAsAttributes.get_imagePathLeft(),
                dilemmaAdminAsAttributes.get_textLeft(),
                NewlyMadeDilemma.getDilemmaId()
        );
        DilemmaOptions dilemmaOptionsDatabase2 = new DilemmaOptions(
                2,
                dilemmaAdminAsAttributes.get_imagePathLeft(),
                dilemmaAdminAsAttributes.get_textLeft(),
                NewlyMadeDilemma.getDilemmaId()
        );
        dao.addDilemmaOptions(dilemmaOptionsDatabase1,dilemmaOptionsDatabase2);

    }
}


