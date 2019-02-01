package nl.hsleiden.service;

import nl.hsleiden.model.*;
import nl.hsleiden.persistence.DilemmaAdminAsObjectsDAO;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DilemmaAdminAsObjectsService {

    private final DilemmaAdminAsObjectsDAO dao;

    @Inject
    public DilemmaAdminAsObjectsService(DilemmaAdminAsObjectsDAO dao){
        this.dao = dao;
    }

    public DilemmaAdminAsObjects getAll() { return dao.getDilemmaAdminAsObject(); }

    public void deleteDilemmaAdmin(int dilemmaId) {
        dao.deleteDilemma(dilemmaId);
    }

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

        int key = dao.addDilemma(dilemmaDatabase);
        if (key == 0) {
            // TODO: handle error
        }

        DilemmaOptions dilemmaOptionsDatabase1 = new DilemmaOptions(
                1,
                dilemmaAdminAsAttributes.get_imagePathLeft(),
                dilemmaAdminAsAttributes.get_textLeft(),
                key

        );
        DilemmaOptions dilemmaOptionsDatabase2 = new DilemmaOptions(
                2,
                dilemmaAdminAsAttributes.get_imagePathRight(),
                dilemmaAdminAsAttributes.get_textRight(),
                key
        );
        dao.addDilemmaOptions(dilemmaOptionsDatabase1, dilemmaOptionsDatabase2);

    }
}


