package nl.hsleiden.persistence;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ApiApplication;
import nl.hsleiden.database.Database;
import nl.hsleiden.model.Dilemma;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This DAO works based on the Hibernate Framework and is for the 'dilemma' table
 *
 * @Author Yme Brugts
 */
@Singleton
public class DilemmaDAO extends AbstractDAO<Dilemma> {

    private final SessionFactory sessionFactory;

    @Inject
    public DilemmaDAO() {
        super(ApiApplication.hibernateBundle.getSessionFactory());
        sessionFactory = ApiApplication.hibernateBundle.getSessionFactory();
    }

    public List<Dilemma> getAll() {
        CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Dilemma> criteria = builder.createQuery(Dilemma.class);
        criteria.from(Dilemma.class);
        return currentSession().createQuery(criteria).getResultList();
    }

    public Dilemma get(int id) {
        return currentSession().get(Dilemma.class, id);
    }

    public Dilemma getByWeeknumberAndPregnant(int id, boolean pregnant) {
        return currentSession().get(Dilemma.class, id);
    }

    public void delete(Dilemma dilemma) {
        currentSession().delete(dilemma);
    }

    public void update(Dilemma dilemma) {
        currentSession().saveOrUpdate(dilemma);
    }

    public Dilemma insert(Dilemma dilemma) {
        return persist(dilemma);
    }
    
}
