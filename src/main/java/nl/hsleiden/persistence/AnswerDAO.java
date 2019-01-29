package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;
import nl.hsleiden.model.Answer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

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
                answer.set_parent_email(rs.getString("parent_email"));
                answer.set_dilemma_id(rs.getInt("dilemma_id"));
                answer.set_answered_time(rs.getString("answered_time"));
                answer.set_answer(rs.getInt("answer"));
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
        String SQL = "INSERT INTO answer VALUES(?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = this.database.getConnection().prepareStatement(SQL);
            statement.setString(1, answer.get_parent_email());
            statement.setInt(2, answer.get_dilemma_id());
            statement.setDate(3, new Date(System.currentTimeMillis()));
            statement.setInt(4, answer.get_answer());

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
