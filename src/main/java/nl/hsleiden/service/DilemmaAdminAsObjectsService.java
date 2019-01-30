package nl.hsleiden.service;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.Dilemma;
import nl.hsleiden.model.DilemmaAdmin;
import nl.hsleiden.model.DilemmaAdminAsAttributes;
import nl.hsleiden.model.DilemmaAdminAsObjects;
import nl.hsleiden.persistence.DilemmaAdminAsObjectsDAO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class DilemmaAdminAsObjectsService {

    private final DilemmaAdminAsObjectsDAO dao;

    @Inject
    public DilemmaAdminAsObjectsService(DilemmaAdminAsObjectsDAO dao){
        this.dao = dao;
    }

    public DilemmaAdminAsObjects getAll() { return dao.getDilemmaAdminAsObject(); }

    public void AddDilemmaAdmin(DilemmaAdminAsAttributes dilemmaAdminAsAttributes) {
        Dilemma dilemmaDatabase = new Dilemma(
                dilemmaAdminAsAttributes.get_dilemmaId(),
                dilemmaAdminAsAttributes.get_subject(),
                dilemmaAdminAsAttributes.is_pregnant(),
                dilemmaAdminAsAttributes.get_weekNr()
        );
        System.out.println("DilemmaId dat is gegeven: " + dilemmaAdminAsAttributes.get_dilemmaId());
        System.out.println(dilemmaDatabase.getDilemmaId());
        dao.addDilemma(dilemmaDatabase);

        System.out.println(dilemmaDatabase);
        dao.addDilemma(dilemmaDatabase);
    }
}


