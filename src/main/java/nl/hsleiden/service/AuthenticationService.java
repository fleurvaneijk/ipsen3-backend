package nl.hsleiden.service;

import java.util.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.basic.BasicCredentials;
import javax.inject.Inject;
import javax.inject.Singleton;
import nl.hsleiden.model.User;
import nl.hsleiden.persistence.UserDAO;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class AuthenticationService implements Authenticator<BasicCredentials, User>, Authorizer<User>
{
    private final UserDAO userDAO;
    
    @Inject
    public AuthenticationService(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException
    {
        User user = userDAO.getByEmailAddress(credentials.getUsername());
        
        if (user != null && user.getPassword().equals(credentials.getPassword()))
        {
            return Optional.of(user);
        }
        
        return Optional.empty();
    }

    @Override
    public boolean authorize(User user, String roleName)
    {
        return user.hasRole(roleName);
    }

    /**
     * With this we want to check whether the plaintext password corresponds to hashed password from the
     * database
     * @param plaintext a simple not hashed password from inputfield
     * @param hashedPassword a hashed password from the database
     * @return bool (true or false)
     * @author Robin Silverio
     */
    public boolean checkPassword(String plaintext, String hashedPassword){
        boolean corresponds = true;
        if (!BCrypt.checkpw(plaintext, hashedPassword))
            corresponds = false;
        return corresponds;
    }
}
