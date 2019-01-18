package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;
import nl.hsleiden.model.Child;

import javax.inject.Singleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ChildDAO {

    private Database database;

    public ChildDAO()
    {

    }

    public List<Child> getAll()
    {
        List<Child> children = new ArrayList<Child>();
        String SQL = "SELECT*FROM child";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = this.database.getConnection().prepareStatement(SQL);
            rs = pstmt.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            while(rs.next()){
                children.add(
                        new Child(
                            rs.getInt("id"),
                            rs.getInt("couple_id"),
                            new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate("birthdate"))
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
                System.out.println("Preparedstatement kinderen selecteren gestopt");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return children;

    }

    public Child get(int id)
    {
        // Voer query uit (middels een prepared statement!)
        // Maak een nieuw Serie object aan met gegevens uit de database
        // Retourneer Serie object
        Child child = null;
        String SQL = "SELECT*FROM child WHERE id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = this.database.getConnection().prepareStatement(SQL);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                child = new Child();
                child.setChildId(rs.getInt("id"));
                child.setKoppelId(rs.getInt("couple_id"));
                child.setBirthdate(new SimpleDateFormat("dd-MM-YYYY").format(rs.getDate("birthdate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                rs.close();
                pstmt.close();
                System.out.println("Preparedstatement individuele kind selecteren gestopt");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return child;
    }

    public void add(Child child)
    {
        String SQL = "INSERT INTO child "
                + "VALUES(DEFAULT, (SELECT id FROM couple WHERE parent_email_1 = ?), ?)";

        PreparedStatement pstmt = null;

        try {

            pstmt = this.database.getConnection().prepareStatement(SQL);

            SimpleDateFormat dateOfBirth = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date date = dateOfBirth.parse(child.getBirthdate());
            java.sql.Date sqlBirthDate = new java.sql.Date(date.getTime());


            pstmt.setString(1, child.getParentMail());
            pstmt.setDate(2, sqlBirthDate);

            pstmt.executeUpdate();

            System.out.println("Kind is toegevoegd");

            pstmt.close();
            System.out.println("Preparedstatement kind toevoegen gestopt");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Child couple)
    {
//        children.set(id, couple); // Doing it later
    }

    public void delete(int id)
    {
        String SQL = "DELETE FROM child WHERE id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = this.database.getConnection().prepareStatement(SQL);
            pstmt.setInt(1, id);
            pstmt.executeQuery();

            System.out.println("kind is verwijderd");

            pstmt.close();
            System.out.println("Preparedstatement kind verwijderen gestopt");
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