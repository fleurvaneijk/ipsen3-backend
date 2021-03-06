package nl.hsleiden.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;

import nl.hsleiden.database.Database;
import nl.hsleiden.model.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Robin Silverio
 */
@Singleton
public class UserDAO
{
    private Database database;
    
    public UserDAO() { }

    public List<User> getAll()
    {
        List<User> users = new ArrayList<User>();
        String SQL = "SELECT*FROM users";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = this.database.getConnection().prepareStatement(SQL);
            rs = pstmt.executeQuery();
            while(rs.next()){
                users.add(
                        new User(
                                rs.getString("email"),
                                rs.getString("firstname"),
                                rs.getString("lastname"),
                                rs.getString("password"),
                                rs.getString("role")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                rs.close();
                pstmt.close();
                pstmt.getConnection().close();

            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return users;

    }

    public List<User> getAllParents()
    {
        List<User> usersOuders = new ArrayList<User>();
        String SQL = "SELECT*FROM users WHERE role = 'OUDER'";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = this.database.getConnection().prepareStatement(SQL);
            rs = statement.executeQuery();
            while(rs.next()){
                usersOuders.add(
                        new User(
                                rs.getString("email"),
                                rs.getString("firstname"),
                                rs.getString("lastname"),
                                rs.getString("password"),
                                rs.getString("role")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                rs.close();
                statement.close();
                statement.getConnection();

            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return usersOuders;

    }

    public List<User> getAllAdmins()
    {
        List<User> usersAdministrators = new ArrayList<User>();
        String SQL = "SELECT*FROM users WHERE role = 'BEHEERDER' OR role = 'MEDEWERKER'";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = this.database.getConnection().prepareStatement(SQL);
            rs = statement.executeQuery();
            while(rs.next()){
                usersAdministrators.add(
                        new User(
                                rs.getString("email"),
                                rs.getString("firstname"),
                                rs.getString("lastname"),
                                rs.getString("password"),
                                rs.getString("role")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                rs.close();
                statement.close();
                statement.getConnection().close();

            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return usersAdministrators;

    }
    
    public User get(String id)
    {
        // Perform a query (by using a prepared statement for security reasons!)
        // Create a new set of user object with properties based on ID
        // Return a user object.
        User user = null;
        String SQL = "SELECT*FROM users WHERE email = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = this.database.getConnection().prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                user = new User();
                user.setEmailAddress(rs.getString("email"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                rs.close();
                pstmt.close();
                pstmt.getConnection().close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return user;
    }
    
    public User getByEmailAddress(String emailAddress)
    {
        Collection<User> usersList = this.getAll();
        Optional<User> result = usersList.stream()
                .filter(user -> user.getEmailAddress().equals(emailAddress))
                .findAny();
        return result.isPresent() ? result.get(): null;
    }
    
    public void add(User[] user)
    {
        for (User u : user){

            String SQL = "INSERT INTO users "
                    + "VALUES(?,?,?,?,?)";

            PreparedStatement pstmt = null;

            String hashedPassword = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()); // hashed password.

            try {

                pstmt = this.database.getConnection().prepareStatement(SQL);

                pstmt.setString(1, u.getEmailAddress());
                pstmt.setString(2, u.getFirstname());
                pstmt.setString(3, u.getLastname());
                pstmt.setString(4, hashedPassword);
                pstmt.setString(5, u.getRole());

                pstmt.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally {
                try{
                    pstmt.close();
                    pstmt.getConnection().close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void update(User user)
    {
        String SQL = "UPDATE users SET email = ?, firstname = ?, lastname = ?, role = ? WHERE email = ?";
        PreparedStatement statement = null;

        try{
            statement = this.database.getConnection().prepareStatement(SQL);
            statement.setString(1, user.getEmailAddress());
            statement.setString(2, user.getFirstname());
            statement.setString(3, user.getLastname());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getEmailAddress());
            statement.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                statement.close();
                statement.getConnection().close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    
    public void delete(String id)
    {
        String SQL = "DELETE FROM users WHERE email = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = this.database.getConnection().prepareStatement(SQL);
            pstmt.setString(1, id);
            pstmt.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                pstmt.close();
                pstmt.getConnection().close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * This is for setting a database to a DAO.
     * @param database
     * @author Robin Silverio
     */
    public void setDatabase(Database database) {
        this.database = database;
    }
}
