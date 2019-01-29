package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;
import nl.hsleiden.model.Answer;

import javax.inject.Singleton;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class AnswerDAO {
    private Database database;

    public AnswerDAO() {
    }

//    public List getSingleAnswer(String email, int id) {
//        List answer = new ArrayList();
//        String SQL = "SELECT * FROM answer WHERE parent_email = ? AND dilemma_id = ?";
//        PreparedStatement statement = null;
//        ResultSet rs = null;
//        try {
//            statement = this.database.getConnection().prepareStatement(SQL);
//            statement.setString(1, email);
//            statement.setInt(2, id);
//            rs = statement.executeQuery();
//            while (rs.next()) {
//                answer = new Answer();
//                answer.set_parent_email(rs.getString("parent_email"));
//                answer.set_dilemma_id(rs.getInt("dilemma_id"));
//                answer.set_answered_time(rs.getString("answered_time"));
//                answer.set_answer(rs.getInt("answer"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                rs.close();
//                statement.close();
//                this.database.getConnection().close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return answer;
//    }

    public List getAllAnswers() {
        String SQL = "SELECT * FROM answer";
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList a = new ArrayList();
        ArrayList answers = new ArrayList();
        try {
            statement = this.database.getConnection().prepareStatement(SQL);
            rs = statement.executeQuery();
            while (rs.next()) {
                a.add(rs.getString("parent_email"));
                a.add(rs.getInt("dilemma_id"));
                a.add(rs.getDate("answered_time"));
                a.add(rs.getInt("answer"));
                answers.add(a);
                System.out.println(a);
                a = new ArrayList();
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
        return answers;
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
            e.getStackTrace();
        }
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
