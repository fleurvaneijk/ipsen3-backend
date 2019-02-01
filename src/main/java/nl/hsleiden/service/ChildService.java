package nl.hsleiden.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.Child;
import nl.hsleiden.model.Couple;
import nl.hsleiden.model.User;
import nl.hsleiden.persistence.ChildDAO;
import nl.hsleiden.persistence.CoupleDAO;
import nl.hsleiden.persistence.UserDAO;

/**
 * Connects the DAO to the resource and handles logic
 * @author Robin Silverio & Fleur van Eijk
 */
@Singleton
public class ChildService extends BaseService<Child>
{
    private final ChildDAO dao;

    @Inject
    public ChildService(ChildDAO dao)
    {
        this.dao = dao;
        this.dao.setDatabase(ApiApplication.getDatabase());
    }

    public Collection<Child> getAll()
    {
        return dao.getAll();
    }

    public Child get(int id)
    {
        return requireResult(dao.get(id));
    }

    public void add(Child user)
    {
        dao.add(user);
    }

    public void update(int id, Child child)
    {
        dao.update(id, child);
    }

    public void delete(int id)
    {
        dao.delete(id);
    }

    public boolean getChildExistsByCoupleId(int coupleId){
        return dao.getChildExistsByCoupleId(coupleId);
    }

    public void updateBirthdate(int coupleId, Date birthdate){
        java.sql.Date birthdateSQL = new java.sql.Date(birthdate.getTime());
        dao.updateBirthdate(coupleId, birthdateSQL);
    }

}