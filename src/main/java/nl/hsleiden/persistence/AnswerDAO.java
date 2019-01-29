package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;
import nl.hsleiden.model.Answer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerDAO {
    private Database database;

    public AnswerDAO() {

    }

    public Answer get(String id) {
        Answer answer = null;
        String SQL = "SELECT * FROM answer WHERE parent_email = ?";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = this.database.getConnection().prepareStatement(SQL);
            statement.setString(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                answer = new Answer();
                answer.setParent_email(rs.getString("parent_email"));
                answer.setDilemma_id(rs.getInt("dilemma_id"));
                answer.setAnswered_time(rs.getDate("answered_time"));
                answer.setAnswer(rs.getInt("answer"));
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
        return answer;
    }

    public void add(Answer answer) {
        String SQL = "INSERT INTO answer VALUES(DEFAULT,?,?,?,?)";
        PreparedStatement statement = null;

        try {
            statement = this.database.getConnection().prepareStatement(SQL);
            statement.setString(1, answer.getParent_email());
            statement.setInt(2, answer.getDilemma_id());
            statement.setDate(3, answer.getAnswered_time());
            statement.setInt(4, answer.getAnswer());

            statement.executeQuery();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
