package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;
import nl.hsleiden.model.DilemmaSubjects;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DilemmaSubjectDAO {
    private Database database;

    public DilemmaSubjectDAO() {

    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public List getSubject(String subject) {
        List dilemmaSubjects = new ArrayList<>();
        String SQL = "SELECT * FROM dilemma_subject WHERE subject = ?";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = this.database.getConnection().prepareStatement(SQL);
            statement.setString(1, subject);
            rs = statement.executeQuery();
            while (rs.next()) {
                dilemmaSubjects.add(rs.getString("subject"));
                dilemmaSubjects.add(rs.getString("link"));
                dilemmaSubjects.add(rs.getInt("number_clicks"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dilemmaSubjects;
    }
}