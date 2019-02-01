package nl.hsleiden.service;

import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.User;
import nl.hsleiden.persistence.UserDAO;

/**
 *
 * @author Robin Silverio
 */
@Singleton
public class UserService extends BaseService<User>
{
    private final UserDAO dao;
    
    @Inject
    public UserService(UserDAO dao)
    {
        this.dao = dao;
        this.dao.setDatabase(ApiApplication.getDatabase());
    }
    
    public Collection<User> getAll()
    {
        return dao.getAll();
    }

    public Collection<User> getAllParents()
    {
        return dao.getAllParents();
    }

    public Collection<User> getAllAdmins()
    {
        return dao.getAllAdmins();
    }
    
    public User get(String id)
    {
        return requireResult(dao.get(id));
    }
    
    public void add(User[] user)
    {
        dao.add(user);
    }
    
    public void update(User user)
    {
        dao.update(user);
    }
    
    public void delete(String id)
    {
        dao.delete(id);
    }
}
