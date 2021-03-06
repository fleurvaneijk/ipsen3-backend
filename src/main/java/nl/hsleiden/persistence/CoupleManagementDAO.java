package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Executes queries on the multiple tables in the database (Couple, parent & child) for the couple-management screen for the admin.
 * @author Fleur van Eijk
 */
public class CoupleManagementDAO {

    private Database database;

    public CoupleManagementDAO()
    {

    }

    /**
     * Gets couple info for the couple management table on the admin page.
     */
    public ArrayList<ArrayList> getCoupleTableInfo() {
        ArrayList coupleTableInfo = new ArrayList();
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            String query =  "SELECT couple.id, " +
                                    "parent_email_1, (SELECT firstname FROM users WHERE email = parent_email_1) AS firstname_1, " +
                                    "parent_email_2, (SELECT firstname FROM users WHERE email = parent_email_2) AS firstname_2, " +
                                    "pregnant, weeks_pregnant " +
                            "FROM couple JOIN users ON(parent_email_1 = email)\n";

            statement = database.getConnection().prepareStatement(query);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                ArrayList row = new ArrayList();
                row.add(resultSet.getInt("id"));
                row.add(resultSet.getString("parent_email_1"));
                row.add(resultSet.getString("firstname_1"));
                row.add(resultSet.getString("parent_email_2"));
                row.add(resultSet.getString("firstname_2"));
                row.add(resultSet.getBoolean("pregnant"));
                row.add(resultSet.getInt("weeks_pregnant"));

                coupleTableInfo.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
                statement.close();
                statement.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return coupleTableInfo;
    }

    public Date getBirthdate(int coupleId) {
        Date childBirthdate = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            String query =  "SELECT birthdate FROM child WHERE couple_id = ?";
            statement = database.getConnection().prepareStatement(query);
            statement.setInt(1, coupleId);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                childBirthdate = resultSet.getDate("birthdate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
                statement.close();
                statement.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return childBirthdate;
    }

    public void changePregnant(int coupleId, boolean pregnant) {
        PreparedStatement statement = null;
        try {
            String query =  "UPDATE couple SET pregnant = ? WHERE id = ?";
            statement = database.getConnection().prepareStatement(query);
            statement.setBoolean(1, pregnant);
            statement.setInt(2, coupleId);
            statement.executeUpdate();
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

    public void changeCouplePregnant(String email2, Boolean pregnant, int weeksPregnant){
        PreparedStatement statement = null;
        try {
            String query =  "UPDATE couple SET pregnant = ?, weeks_pregnant = ? WHERE parent_email_2 = ?";
            statement = database.getConnection().prepareStatement(query);
            statement.setBoolean(1, pregnant);
            statement.setInt(2, weeksPregnant);
            statement.setString(3, email2);
            statement.executeUpdate();
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

    public void changeCoupleComplete(String mail, int weeksPregnant, int lastWeekAnsered){
        PreparedStatement statement = null;
        try {
            String query =  "UPDATE couple SET weeks_pregnant = ?, last_answer_week_no = ? WHERE parent_email_2 = ?";
            statement = database.getConnection().prepareStatement(query);
            statement.setInt(1, weeksPregnant);
            statement.setInt(2, lastWeekAnsered);
            statement.setString(3, mail);
            statement.executeUpdate();
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

    public void changePregnantWeeks(int coupleId, int weeks) {
        PreparedStatement statement = null;
        try {
            String query =  "UPDATE couple SET weeks_pregnant = ? WHERE id = ?";
            statement = database.getConnection().prepareStatement(query);
            statement.setInt(1, weeks);
            statement.setInt(2, coupleId);
            statement.executeUpdate();
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
