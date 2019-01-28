package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;
import nl.hsleiden.model.DilemmaOptions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DilemmaOptionsDAO {

    private Database database;

    public DilemmaOptionsDAO() {

    }

    public void setDatabase(Database database) {
        this.database = database;
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
