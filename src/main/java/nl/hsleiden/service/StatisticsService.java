package nl.hsleiden.service;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.persistence.StatisticsDAO;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

/**
 * @author Fleur van Eijk
 */
public class StatisticsService {

    private final StatisticsDAO dao;

    @Inject
    public StatisticsService(StatisticsDAO dao) {
        this.dao = dao;
        this.dao.setDatabase(ApiApplication.getDatabase());
    }

    public List getRatingPerSubject() {
        return dao.getRatingPerSubject();
    }

}
