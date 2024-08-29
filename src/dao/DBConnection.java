package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
    private static final String URL = "jdbc:sqlserver://DESKTOP-7TBVHDO\\SQLEXPRESS:1433;databaseName=inventory_db;encrypt=false;integratedSecurity=true;";
    private static final String USER = ""; 
    private static final String PASSWORD = ""; 

    static {
        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            
            connection = DriverManager.getConnection(URL);
            System.out.println("Connection to database established.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
}
