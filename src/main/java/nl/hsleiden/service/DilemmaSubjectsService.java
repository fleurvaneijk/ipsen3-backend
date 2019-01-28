package nl.hsleiden.service;

import nl.hsleiden.ApiApplication;

import nl.hsleiden.model.DilemmaSubjects;
import nl.hsleiden.persistence.DilemmaSubjectDAO;

import javax.inject.Inject;
import java.util.Collection;

public class DilemmaSubjectsService extends  BaseService {

    private final DilemmaSubjectDAO dao;

    @Inject
    public DilemmaSubjectsService(DilemmaSubjectDAO dao){
        this.dao = dao;
        this.dao.setDatabase(ApiApplication.getDatabase());
    }

    public Collection<DilemmaSubjects> getAll(String subject) { return dao.getSubjects(subject); }
}
