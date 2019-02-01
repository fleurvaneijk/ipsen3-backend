package nl.hsleiden.service;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.DilemmaSubject;
import nl.hsleiden.persistence.DilemmaSubjectDAO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.List;

@Singleton
public class DilemmaSubjectsService extends BaseService {

    private final DilemmaSubjectDAO dao;

    @Inject
    public DilemmaSubjectsService(DilemmaSubjectDAO dao){
        this.dao = dao;
        this.dao.setDatabase(ApiApplication.getDatabase());
    }

    public List getAllSubjects() { return dao.getAllSubjects(); }

    public Collection<DilemmaSubject> getSubject(String subject) {
        List a = dao.getSubject(subject);
        return dao.getSubject(subject);

    }
}
