package nl.hsleiden.service;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.SaveRating;
import nl.hsleiden.persistence.SaveRatingDao;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SaveRatingService extends BaseService<SaveRating> {
    private final SaveRatingDao dao;

    @Inject
    public SaveRatingService(SaveRatingDao dao) {
        this.dao = dao;
        this.dao.setDatabase(ApiApplication.getDatabase());
    }

    public void add(SaveRating saveRating) {
        dao.add(saveRating);
    }
}
