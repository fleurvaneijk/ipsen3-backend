package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;
import nl.hsleiden.model.Couple;
import nl.hsleiden.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Singleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Singleton
public class CoupleDAO {

    private Database database;

    public CoupleDAO()
    {

    }

    public List<Couple> getAll()
    {
        List<Couple> couples = new ArrayList<Couple>();
        String SQL = "SELECT*FROM couple";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = this.database.connect().prepareStatement(SQL);
            rs = pstmt.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            while(rs.next()){
                couples.add(
                        new Couple(
                                rs.getString("parent_email_1"),
                                rs.getString("parent_email_2"),
                                rs.getBoolean("pregnant"),
                                rs.getInt("weeks_pregnant"),
                                rs.getInt("last_answer_week_no")
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
                System.out.println("Preparedstatement koppels selecteren gestopt");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return couples;

    }

    public Couple get(int id)
    {
        // Voer query uit (middels een prepared statement!)
        // Maak een nieuw Serie object aan met gegevens uit de database
        // Retourneer Serie object
        Couple couple = null;
        String SQL = "SELECT*FROM couple WHERE id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = this.database.connect().prepareStatement(SQL);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                couple = new Couple();
                couple.setParentMail1(rs.getString("parent_email_1"));
                couple.setParentMail2(rs.getString("parent_email_2"));
                couple.setPregnant(rs.getBoolean("pregnant"));
                couple.setWeeksPregnant(rs.getInt("weeks_pregnant"));
                couple.setLastAnswerWeekNo(rs.getInt("last_answer_week_no"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                rs.close();
                pstmt.close();
                System.out.println("Preparedstatement individuele koppel selecteren gestopt");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return couple;
    }

    public void add(Couple couple)
    {
        String SQL = "INSERT INTO couple "
                + "VALUES(DEFAULT,?,?,?,?,?)";

        PreparedStatement pstmt = null;

        try {

            pstmt = this.database.connect().prepareStatement(SQL);


            pstmt.setString(1, couple.getParentMail1());
            pstmt.setString(2, couple.getParentMail2());
            pstmt.setBoolean(3, couple.getPregnant());
            pstmt.setInt(4, couple.getWeeksPregnant());
            pstmt.setInt(5, couple.getLastAnswerWeekNo());

            pstmt.executeUpdate();

            System.out.println("Koppel is toegevoegd");

            pstmt.close();
            System.out.println("Preparedstatement koppel toevoegen gestopt");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(int id, Couple couple)
    {
//        couples.set(id, couple); // Doing it later
    }

    public void delete(int id)
    {
        String SQL = "DELETE FROM couple WHERE id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = this.database.connect().prepareStatement(SQL);
            pstmt.setInt(1, id);
            pstmt.executeQuery();

            System.out.println("Koppel is verwijderd");

            pstmt.close();
            System.out.println("Preparedstatement koppel verwijderen gestopt");
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
