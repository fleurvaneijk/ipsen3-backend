package nl.hsleiden.service;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.Answer;
import nl.hsleiden.persistence.AnswerDAO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;

@Singleton
public class AnswerService extends BaseService {
    private final AnswerDAO dao;

    @Inject
    public AnswerService(AnswerDAO dao) {
        this.dao = dao;
        this.dao.setDatabase(ApiApplication.getDatabase());
    }


    public Collection getSingleAnswer(String email, int id) {
        return dao.getSingleAnswer(email, id);
    }

    public Collection getAllAnswers() {
        return dao.getAllAnswers();
    }

    public void add(Answer answer) {
        dao.add(answer);
    }
}
