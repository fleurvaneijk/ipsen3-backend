package nl.hsleiden.service;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.DilemmaOption;
import nl.hsleiden.persistence.DilemmaOptionDAO;

import javax.inject.Inject;
import java.util.Collection;

public class DilemmaOptionsService extends  BaseService {

    private final DilemmaOptionDAO dao;

    @Inject
    public DilemmaOptionsService(DilemmaOptionDAO dao){
        this.dao = dao;
        this.dao.setDatabase(ApiApplication.getDatabase());
    }

    public Collection<DilemmaOption> getAll(int id) { return dao.getOptions(id); }
}
