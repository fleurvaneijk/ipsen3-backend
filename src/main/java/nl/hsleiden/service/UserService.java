package nl.hsleiden.service;

import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.User;
import nl.hsleiden.persistence.UserDAO;

/**
 *
 * @author Peter van Vliet
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
    
    public User get(String id)
    {
        return requireResult(dao.get(id));
    }

    public Collection<User> getAllAdmins()
    {
        return dao.getAllAdmins();
    }
    
    public void add(User[] user)
    {
        dao.add(user);
    }
    
    public void update(User authenticator, String id, User user)
    {
        // Controleren of deze gebruiker wel bestaat
        User oldUser = get(id);
        
        if (!authenticator.hasRole("ADMIN"))
        {
            // Vaststellen dat de geauthenticeerde gebruiker
            // zichzelf aan het aanpassen is
            assertSelf(authenticator, oldUser);
        }
        
        dao.update(id, user);
    }
    
    public void delete(String id)
    {
//        // Controleren of deze gebruiker wel bestaat
//        User user = get(id);
        dao.delete(id);
    }
}
