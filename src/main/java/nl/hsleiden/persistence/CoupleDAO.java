package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;
import nl.hsleiden.model.Couple;

import javax.inject.Singleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            pstmt = this.database.getConnection().prepareStatement(SQL);
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
                this.database.getConnection().close();
                System.out.println("Preparedstatement koppels selecteren gestopt");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        System.out.println(couples);
        return couples;

    }

    public Couple get(String email)
    {
        Couple couple = null;
        String SQL = "SELECT*FROM couple WHERE ? IN (parent_email_1, parent_email_2)";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = this.database.getConnection().prepareStatement(SQL);
            pstmt.setString(1, email);
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
                this.database.getConnection().close();
                System.out.println("Preparedstatement individuele koppel selecteren gestopt YOYOYOYOY");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return couple;
    }

    public int getCoupleId(String email)
    {
        int id = 0;
        String SQL = "SELECT id FROM couple WHERE ? = parent_email_1 or ? = parent_email_2";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = this.database.getConnection().prepareStatement(SQL);
            pstmt.setString(1, email);
            pstmt.setString(2, email);
            rs = pstmt.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                rs.close();
                this.database.getConnection().close();
                System.out.println("Koppel ID selecteren gestopt");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return id;
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
            pstmt = this.database.getConnection().prepareStatement(SQL);
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
                this.database.getConnection().close();
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

            pstmt = this.database.getConnection().prepareStatement(SQL);


            pstmt.setString(1, couple.getParentMail1());
            pstmt.setString(2, couple.getParentMail2());
            pstmt.setBoolean(3, couple.getPregnant());
            pstmt.setInt(4, couple.getWeeksPregnant());
            pstmt.setInt(5, couple.getLastAnswerWeekNo());

            pstmt.executeUpdate();

            System.out.println("Koppel is toegevoegd");

            pstmt.close();
            this.database.getConnection().close();
            System.out.println("Preparedstatement koppel toevoegen gestopt");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(String id, Couple couple)
    {
//        couples.set(id, couple); // Doing it later
    }

    public void delete(int id)
    {
        String SQL = "DELETE FROM couple WHERE id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = this.database.getConnection().prepareStatement(SQL);
            pstmt.setInt(1, id);
            pstmt.executeQuery();

            System.out.println("Koppel is verwijderd");

            pstmt.close();
            this.database.getConnection().close();
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
