package nl.hsleiden.service;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.DilemmaAdminAsObjects;
import nl.hsleiden.persistence.DilemmaAdminAsObjectsDAO;

import javax.inject.Inject;
import java.util.List;

public class DilemmaAdminAsObjectsService {

    private final DilemmaAdminAsObjectsDAO dao;

    @Inject
    public DilemmaAdminAsObjectsService(DilemmaAdminAsObjectsDAO dao){
        this.dao = dao;
    }

    public DilemmaAdminAsObjects getAll() { return dao.getDilemmaAdminAsObject(); }
}

