package nl.hsleiden.service;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.Dilemma;
import nl.hsleiden.persistence.DilemmaDAO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;

@Singleton
public class DilemmaService extends BaseService<Dilemma>{

    private final DilemmaDAO dao;

    @Inject
    public DilemmaService(DilemmaDAO dao){
        this.dao = dao;
//        this.dao.setDatabase(ApiApplication.getDatabase());
    }

    public Collection<Dilemma> getAll(){
        return dao.getAll();
    }

    public Dilemma get(int id){
        return requireResult(dao.get(id));
    }
}
