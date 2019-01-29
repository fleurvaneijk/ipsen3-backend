package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;
import nl.hsleiden.model.DilemmaOptions;

import javax.inject.Singleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This DAO is for the 'dilemma_option' table
 *
 * @Author Yme Brugts, Dennis van Beelen
 */
@Singleton
public class DilemmaOptionsDAO {

    private Database database;

    public DilemmaOptionsDAO() {

    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    /**
     * @Author Yme Brugts
     */
    public List<DilemmaOptions> getAll()
    {
        List<DilemmaOptions> dilemmaOptions = new ArrayList<DilemmaOptions>();
        String SQL = "SELECT*FROM dilemma_option";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = this.database.getConnection().prepareStatement(SQL);
            rs = pstmt.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            while(rs.next()){
                dilemmaOptions.add(
                        new DilemmaOptions(
                                rs.getInt("id"),
                                rs.getString("image"),
                                rs.getString("text"),
                                rs.getInt("dilemma_id")
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
        System.out.println(">>>>>>>>>>>>>>>>>>" +dilemmaOptions);
        return dilemmaOptions;

    }



    public List<DilemmaOptions> getOptions(int id) {
        List<DilemmaOptions> dilemmaOptions = new ArrayList<>();
        String SQL = "SELECT * FROM dilemma_option WHERE dilemma_id = ?";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = this.database.getConnection().prepareStatement(SQL);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                dilemmaOptions.add(
                        new DilemmaOptions(
                                rs.getInt("id"),
                                rs.getString("image"),
                                rs.getString("text"),
                                rs.getInt("dilemma_id")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                statement.close();
                this.database.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dilemmaOptions;
    }
}
