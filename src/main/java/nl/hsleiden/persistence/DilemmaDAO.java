package nl.hsleiden.persistence;

import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.HibernateBundle;
import nl.hsleiden.ApiApplication;
import nl.hsleiden.database.Database;
import nl.hsleiden.model.Dilemma;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Connection;
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

    public void delete(int dilemmaId) {
        Session session ;
        Dilemma myObject ;

        session = sessionFactory.getCurrentSession();
        myObject = (Dilemma)session.load(Dilemma.class,dilemmaId);
        session.delete(myObject);
        //This makes the pending delete to be done
        session.flush();
    }

    public void update(Dilemma dilemma) {
        currentSession().saveOrUpdate(dilemma);
    }

    public int insert(Dilemma dilemma) {
        try {
            Connection connection = ApiApplication.getDatabase().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO dilemma (subject, pregnant, week_no) VALUES (?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, dilemma.getSubject());
            statement.setBoolean(2, dilemma.isPregnant());
            statement.setInt(3, dilemma.getWeekNr());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new NoResultException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
}
