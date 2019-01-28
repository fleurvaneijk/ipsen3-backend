package nl.hsleiden.service;

import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.Couple;
import nl.hsleiden.model.User;
import nl.hsleiden.persistence.CoupleDAO;
import nl.hsleiden.persistence.UserDAO;

/**
 *
 * @author Robin Silverio
 */
@Singleton
public class CoupleService extends BaseService<Couple>
{
    private final CoupleDAO dao;

    @Inject
    public CoupleService(CoupleDAO dao)
    {
        this.dao = dao;
        this.dao.setDatabase(ApiApplication.getDatabase());
    }

    public List<Couple> getAll()
    {
        return dao.getAll();
    }

    public Couple get(int id)
    {
        return requireResult(dao.get(id));
    }

    public void add(Couple user)
    {
        dao.add(user);
    }

    public void update(int id, Couple couple)
    {
        // Controleren of deze gebruiker wel bestaat
        Couple oldCouple = get(id);
        dao.update(id, couple);
    }

    public void delete(int id)
    {
        dao.delete(id);
    }
}