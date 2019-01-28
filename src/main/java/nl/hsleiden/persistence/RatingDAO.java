package nl.hsleiden.persistence;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.Rating;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * This DAO works based on the Hibernate Framework.
 *
 * @return list of all rating stored in the database
 * @Author Yme Brugts
 */
@Singleton
public class RatingDAO extends AbstractDAO<Rating> {

    private final SessionFactory sessionFactory;

    @Inject
    public RatingDAO() {
        super(ApiApplication.hibernateBundle.getSessionFactory());
        sessionFactory = ApiApplication.hibernateBundle.getSessionFactory();
    }

    public List<Rating> getAll() {
        CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Rating> criteria = builder.createQuery(Rating.class);
        criteria.from(Rating.class);
        return currentSession().createQuery(criteria).getResultList();
    }

    public Rating get(int id) {
        return currentSession().get(Rating.class, id);
    }

    public void delete(Rating rating) {
        currentSession().delete(rating);
    }

    public void update(Rating rating) {
        currentSession().saveOrUpdate(rating);
    }

    public Rating insert(Rating rating) {
        return persist(rating);
    }

}
