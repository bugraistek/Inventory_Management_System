package dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class DBConnectionTest {

    public static void main(String[] args) {
        // Test the database connection
        try (Connection connection = DBConnection.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Database connection is successful.");

                // Retrieve and print some metadata
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println("Database Product Name: " + metaData.getDatabaseProductName());
                System.out.println("Database Product Version: " + metaData.getDatabaseProductVersion());
                System.out.println("Driver Name: " + metaData.getDriverName());
                System.out.println("Driver Version: " + metaData.getDriverVersion());
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
