package nl.hsleiden.service;

import nl.hsleiden.ApiApplication;

import nl.hsleiden.model.DilemmaSubjects;
import nl.hsleiden.persistence.DilemmaSubjectDAO;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

public class DilemmaSubjectsService extends  BaseService {

    private final DilemmaSubjectDAO dao;

    @Inject
    public DilemmaSubjectsService(DilemmaSubjectDAO dao){
        this.dao = dao;
        this.dao.setDatabase(ApiApplication.getDatabase());
    }

    public List getAllSubjecs() { return dao.getAllSubjects(); }

    public Collection<DilemmaSubjects> getSubject(String subject) {
        return dao.getSubject(subject);

    }
}
