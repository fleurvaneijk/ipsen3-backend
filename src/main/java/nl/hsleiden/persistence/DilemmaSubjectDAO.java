package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;
import nl.hsleiden.model.DilemmaSubjectAdmin;

import javax.inject.Singleton;
import javax.security.auth.Subject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DilemmaSubjectDAO {
    private Database database;

    public DilemmaSubjectDAO() {

    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public void addSubject(DilemmaSubjectAdmin subject) {
        boolean exists = false;

        ArrayList AllSubjectList = new ArrayList();
        List dilemmaSubjectList = getAllSubjects();
        System.out.println(dilemmaSubjectList);
        for (int i=0;i < dilemmaSubjectList.size(); i++) {
            if (subject.getSubject().equals(dilemmaSubjectList.get(i).toString())) {
                exists = true;
            }
        }
        if (!exists) {
            addSubjectWithoutCheck(subject);
        }

    }

    public void addSubjectWithoutCheck(DilemmaSubjectAdmin subject) {
        String SQL = "INSERT INTO dilemma_subject VALUES(?)";
        PreparedStatement statement = null;
        try {
            statement = this.database.getConnection().prepareStatement(SQL);
            statement.setString(1, subject.getSubject());
            statement.executeUpdate();
            statement.close();
            this.database.getConnection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                this.database.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public List getSubject(String subject) {
        List dilemmaSubjectProperties = new ArrayList<>();
        String SQL = "SELECT * FROM dilemma_subject WHERE subject = ?";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = this.database.getConnection().prepareStatement(SQL);
            statement.setString(1, subject);
            rs = statement.executeQuery();
            while (rs.next()) {
                dilemmaSubjectProperties.add(rs.getString("subject"));
                dilemmaSubjectProperties.add(rs.getString("link"));
                dilemmaSubjectProperties.add(rs.getInt("number_clicks"));
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
        return dilemmaSubjectProperties;
    }

    public List getAllSubjectsAdmin() {
        List<DilemmaSubjectAdmin> subjects = new ArrayList<DilemmaSubjectAdmin>();
        ResultSet resultSet = null;

        try {
            String query =  "SELECT subject, link FROM dilemma_subject";

            PreparedStatement statement = database.getConnection().prepareStatement(query);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                subjects.add(
                        new DilemmaSubjectAdmin(
                                resultSet.getString("subject"),
                                resultSet.getString("link")
                        )
                );
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
        return subjects;
    }

    public List getAllSubjects() {
        ArrayList subjects = new ArrayList();
        ResultSet resultSet = null;

        try {
            String query =  "SELECT subject FROM dilemma_subject ORDER BY subject";

            PreparedStatement statement = database.getConnection().prepareStatement(query);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                subjects.add(resultSet.getString("subject"));
            }
            statement.close();
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
        return subjects;
    }
}