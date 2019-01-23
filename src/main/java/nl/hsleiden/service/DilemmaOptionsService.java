package nl.hsleiden.service;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.DilemmaOptions;
import nl.hsleiden.persistence.DilemmaOptionsDAO;

import javax.inject.Inject;
import java.util.Collection;

public class DilemmaOptionsService extends  BaseService {

    private final DilemmaOptionsDAO dao;

    @Inject
    public DilemmaOptionsService(DilemmaOptionsDAO dao){
        this.dao = dao;
        this.dao.setDatabase(ApiApplication.getDatabase());
    }

    public Collection<DilemmaOptions> getAll(int id) { return dao.getOptions(id); }
}
