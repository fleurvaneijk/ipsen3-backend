package nl.hsleiden.database;

import java.sql.*;

public class Database {
    // Dit zijn de credentials om toegang te krijgen naar de database
    private final String url = "jdbc:postgresql://localhost/dubio";
    private final String user = "postgres";
    private final String pass = "postgres";
    private static Connection conn = null;
    PreparedStatement statement = null;
    ResultSet rs = null;

    //Constructor
    public Database() {
    }

    /**
     * We can connect with the database using this function
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
    }

    /**
     * Connects tot the database Postgresql "DUBIO"
     * @return a Connection object
     * @author Robin Silverio
     */
    public Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection(url,user,pass);
        return conn;
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
     * This is is for closing the connection
     * @author Fleur van Eijk
     */
    public void disconnect(){
        try{
            if(conn != null){
                conn.close();
            }
        }catch (SQLException sqlEx)
        {
            sqlEx.printStackTrace();

        }
    }
}
