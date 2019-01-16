package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;
import nl.hsleiden.model.Dilemma;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DilemmaDAO {

    private Database database;

    public DilemmaDAO() {

    }

    public List<Dilemma> getAll() {
        List<Dilemma> dilemmas = new ArrayList<>();
        String SQL = "SELECT*FROM dilemma";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = this.database.connect().prepareStatement(SQL);
            rs = statement.executeQuery();
            while (rs.next()) {
                dilemmas.add(
                        new Dilemma(
                                rs.getInt("id"),
                                rs.getString("subject"),
                                rs.getBoolean("pregnant"),
                                rs.getInt("week_no")
                        )
                );
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
        System.out.println(dilemmas);
        return dilemmas;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public Dilemma get(int id) {
        Dilemma dilemma = null;
        String SQL = "SELECT * FROM dilemma WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = this.database.connect().prepareStatement(SQL);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                dilemma = new Dilemma();
                dilemma.setDilemmaId(rs.getInt("id"));
                dilemma.setSubject(rs.getString("subject"));
                dilemma.setPregnant(rs.getBoolean("pregnant"));
                dilemma.setWeekNr(rs.getInt("week_no"));
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
        return dilemma;
    }

}
