package nl.hsleiden.service;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.Answer;
import nl.hsleiden.persistence.AnswerDAO;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AnswerService extends BaseService<Answer> {
    private final AnswerDAO dao;

    @Inject
    public AnswerService(AnswerDAO dao) {
        this.dao = dao;
        this.dao.setDatabase(ApiApplication.getDatabase());
    }

    public Answer get(String id) {
        return requireResult(dao.get(id));
    }

    public void add(Answer answer) {
        dao.add(answer);
    }
}
