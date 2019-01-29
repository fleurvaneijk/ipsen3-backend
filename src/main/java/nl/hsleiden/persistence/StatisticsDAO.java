package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;

import javax.inject.Singleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO that gets statistics data from multiple tables.
 * @author Fleur van Eijk
 */
@Singleton
public class StatisticsDAO {

    private Database database;
    private ResultSet resultSet = null;

    public StatisticsDAO() {

    }

    /**
     * Gets the average rating of each dilemma subject.
     * For diagram 1 on the statistics page.
     */
    public List getRatingPerSubject() {
        ArrayList ratingPerSubjectList = new ArrayList();

        try {
            String query =  "SELECT couple.pregnant, dilemma.subject, ROUND(AVG(rating_dilemma + rating_time)/2::numeric,1) AS rating " +
                            "FROM rating " +
                                "JOIN dilemma ON(dilemma_id = dilemma.id) " +
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
                this.database.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ratingPerSubjectList;
    }

    /**
     * Gets the amount of times a dilemma has been answered &
     * the amount of times a feedback link has been clicked for that dilemma.
     * For diagram 2 on the statistics page.
     */
    public List getAmountDilemmaAnswersFeedbackClicks() {
        ArrayList dilemmaAnswersClicksAmount = new ArrayList();

        try {
            String query =  "SELECT dilemma_id, COUNT(dilemma_id) AS answer_amount, number_clicks " +
                            "FROM dilemma d " +
                                "JOIN answer ON(id = dilemma_id) " +
                                "JOIN dilemma_subject s ON(d.subject = s.subject) " +
                            "GROUP BY dilemma_id, number_clicks " +
                            "ORDER BY dilemma_id";

            PreparedStatement statement = database.getConnection().prepareStatement(query);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                ArrayList row = new ArrayList();
                row.add(resultSet.getInt("dilemma_id"));
                row.add(resultSet.getInt("answer_amount"));
                row.add(resultSet.getInt("number_clicks"));

                dilemmaAnswersClicksAmount.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
                this.database.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dilemmaAnswersClicksAmount;
    }

    /**
     * Gets the answers DateTimes to see what the most popular dates and times are to answer dilemmas.
     * For diagram 4 and 5 on the statistics page.
     */
    public List getAnswerDateTimes() {
        ArrayList<String> answerDateTimes = new ArrayList<>();

        try {
            String query =  "SELECT answered_time FROM answer";

            PreparedStatement statement = database.getConnection().prepareStatement(query);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                answerDateTimes.add(resultSet.getString("answered_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
                this.database.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return answerDateTimes;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
