package nl.hsleiden.service;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.Dilemma;
import nl.hsleiden.persistence.DilemmaDAO;
import nl.hsleiden.persistence.DilemmaDAONoHybernate;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;

@Singleton
public class DilemmaService extends BaseService<Dilemma>{

    private final DilemmaDAO dao;
    private final DilemmaDAONoHybernate daoNoHybernate;

    @Inject
    public DilemmaService(DilemmaDAO dao, DilemmaDAONoHybernate daoNoHybernate){
        this.dao = dao;
        this.daoNoHybernate = daoNoHybernate;
        daoNoHybernate.setDatabase(ApiApplication.getDatabase());
    }

    public Collection<Dilemma> getAll(){
        return dao.getAll();
    }

    public Dilemma get(int id){
        return requireResult(dao.get(id));
    }

    public Dilemma getByPregnant(int id, boolean pregnant){


        return daoNoHybernate.getByWeeknumberAndPregnant(id, pregnant);
    }
}
