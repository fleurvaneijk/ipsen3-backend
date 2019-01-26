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
 * @author Peter van Vliet
 */
@Singleton
public class UserDAO
{
    private final List<User> users;
    private final List<User> admins;
    private Database database;
    
    public UserDAO() {
        users = new ArrayList<>();
        admins = new ArrayList<>();
    }
    
    public List<User> getAll()
    {
//        List<User> ouderList = new ArrayList<>();
//        String SQL = "SELECT*FROM users WHERE role = 'PARENT'";
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            pstmt = this.database.getConnection().prepareStatement(SQL);
//            rs = pstmt.executeQuery();
//            int columnCount = rs.getMetaData().getColumnCount();
//            while(rs.next()){
//                ouderList.add(
//                        new User(
//                                rs.getString("email"),
//                                rs.getString("firstname"),
//                                rs.getString("lastname"),
//                                rs.getString("password"),
//                                rs.getString("role")
//                        )
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            try{
//                rs.close();
//                pstmt.close();
//                System.out.println("Preparedstatement users selecteren gestopt");
//            }
//            catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
//
//        return ouderList;
        List<User> users = new ArrayList<User>();
        String SQL = "SELECT*FROM users";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = this.database.getConnection().prepareStatement(SQL);
            rs = pstmt.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
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
                System.out.println("Preparedstatement users selecteren gestopt");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return users;

    }

    public List<User> getAllAdmins() {
        List<User> users = new ArrayList<User>();
        String SQL = "SELECT * FROM users WHERE role = ? OR role = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = this.database.getConnection().prepareStatement(SQL);
            pstmt.setString(1, "ADMIN");
            pstmt.setString(1, "MEDEWERKER");
            rs = pstmt.executeQuery();
            while(rs.next()){
                admins.add(
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
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return admins;

    }
    
    public User get(String id)
    {
        // Voer query uit (middels een prepared statement!)
        // Maak een nieuw Serie object aan met gegevens uit de database
        // Retourneer Serie object
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
                System.out.println("Preparedstatement individuele gebruiker selecteren gestopt");
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

                System.out.println("Gebruiker is toegevoegd");

                pstmt.close();
                System.out.println("Preparedstatement gebruiker toevoegen gestopt");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void update(String id, User user)
    {
//        users.set(id, user); // Doing it later
    }
    
    public void delete(String id)
    {
        String SQL = "DELETE FROM users WHERE email = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = this.database.getConnection().prepareStatement(SQL);
            pstmt.setString(1, id);
            pstmt.executeQuery();

            System.out.println("Gebruiker is verwijderd");

            pstmt.close();
            System.out.println("Preparedstatement gebruiker verwijderd gestopt");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This is for setting a database to a DAO.
     * @param database
     */
    public void setDatabase(Database database) {
        this.database = database;
    }
}
