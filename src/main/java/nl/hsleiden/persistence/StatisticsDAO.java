package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO that gets statistics data from multiple tables.
 * @author Fleur van Eijk
 */
public class StatisticsDAO {

    private Database database;
    private ResultSet resultSet = null;

    public StatisticsDAO() {

    }

    public List getRatingPerSubject() {
        ArrayList ratingPerSubjectList = new ArrayList();

        try {
            String query =  "SELECT couple.pregnant, dilemma.subject, ROUND(AVG(rating_dilemma + rating_time)/2::numeric,1) AS rating " +
                            "FROM rating " +
                                "JOIN dilemma ON(dilemma_id = id) " +
                                "JOIN couple ON(parent_email = parent_email_1 OR parent_email = parent_email_2) " +
                            "GROUP BY couple.pregnant, dilemma.subject";

            PreparedStatement statement = database.getConnection().prepareStatement(query);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                ArrayList row = new ArrayList();
                row.add(resultSet.getBoolean("pregnant"));
                row.add(resultSet.getString("subject"));
                row.add(resultSet.getDouble("rating"));

                ratingPerSubjectList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ratingPerSubjectList;

    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
