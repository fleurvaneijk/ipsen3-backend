package nl.hsleiden.database;

import java.sql.*;

public class Database {
    // Dit zijn de credentials om toegang te krijgen naar de database
    private final String url = "jdbc:postgresql://www.ipsen3.nl/dubio";
    private final String user = "postgres";
    private final String pass = "postgres";
    private static Connection conn = null;
    PreparedStatement statement = null;
    ResultSet rs = null;

    //Constructor
    public Database() {
    }

    /**
     * Hiermee willen we ervoor zorgen dat de applicatie met de database verbonden is
     * @author Yme Brugts, Robin Silverio, Fleur van Eijk
     */
    public void connect(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (conn != null) {
            System.out.println("Database is connected.");
        } else {
            System.out.println("Couldn't getConnection with the database.");
        }
    }

    /**
     * Verbindt met de PostgreSQL server naar de database DUBIO*
     * @return a Connection object
     * @author Robin Silverio
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    private boolean hasConnection(){
        if (conn != null) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Dit is voor het sluiten van verbinding met de PostgreSQL server
     * @author Fleur van Eijk
     */
    public void disconnect(){
        try{
            if(statement != null){
                statement.close();
            }
            if(rs != null){
                rs.close();
            }
            if(conn != null){
                conn.close();
            }
        }catch (SQLException sqlEx)
        {
            sqlEx.printStackTrace();
            System.out.println("Couldn't disconnect from the database");
        }
    }
}
