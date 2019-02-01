package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;
import nl.hsleiden.model.Dilemma;

import javax.inject.Singleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DilemmaDAONoHybernate {
    private Database database;

    public DilemmaDAONoHybernate()
    {

    }

    public Dilemma getByWeeknumberAndPregnant(int id, boolean pregnant)
    {
        // Voer query uit (middels een prepared statement!)
        // Maak een nieuw Serie object aan met gegevens uit de database
        // Retourneer Serie object

        Dilemma dilemma = null;
        String SQL = "SELECT * FROM dilemma WHERE week_no = ? AND pregnant = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = this.database.getConnection().prepareStatement(SQL);
            pstmt.setInt(1, id);
            pstmt.setBoolean(2, pregnant);
            rs = pstmt.executeQuery();
            while(rs.next()){

                dilemma = new Dilemma();
                dilemma.setWeekNr(rs.getInt("week_no"));
                dilemma.setPregnant(rs.getBoolean("pregnant"));
                dilemma.setSubject(rs.getString("subject"));
                dilemma.setDilemmaId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                rs.close();
                pstmt.close();
                this.database.getConnection().close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        return dilemma;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

}
