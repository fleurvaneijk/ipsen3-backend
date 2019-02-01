package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;
import nl.hsleiden.model.SaveRating;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveRatingDao {
    private Database database;

    public SaveRatingDao() {

    }

    public void add(SaveRating SaveRating) {
        String SQL = "INSERT INTO rating VALUES(DEFAULT,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = this.database.getConnection().prepareStatement(SQL);
            statement.setString(1, SaveRating.get_parent_email());
            statement.setInt(2, SaveRating.get_dilemma_id());
            statement.setInt(3, SaveRating.get_rating_time());
            statement.setInt(4, SaveRating.get_rating_dilemma());

            statement.executeQuery();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                statement.close();
                statement.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
