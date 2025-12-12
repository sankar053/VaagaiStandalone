package com.iii.VaagaiStandalone.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.iii.VaagaiStandalone.config.DatabaseConfig;

public class DeleteUtility
{
   
    public static boolean deleteCategory(int categoryID) throws ClassNotFoundException, SQLException
    {
        String sql = "DELETE FROM Category WHERE categoryID = ?";
        int deleted = 0;
        try
        {
            try (Connection connection = DatabaseConfig.getDBConnection();
                 PreparedStatement statement = connection.prepareStatement(sql))
                 {
                    statement.setInt(1, categoryID);
                    // executeUpdate() -> DELETE/INSERT/UPDATE
                    deleted = statement.executeUpdate(); //  Deleting process produce integer(0 or 1) to check delete process

                 }
        } catch (ClassNotFoundException e) {
            // This catch block handles the error if the JAR file is missing from the classpath.
            System.err.println("Error: MySQL JDBC Driver not found. Please ensure the JAR is correctly configured.");
            e.printStackTrace();
        } catch (SQLException e) {
            // This catch block handles connection issues (wrong password, server down, etc.)
            System.err.println("Connection Failed or SQL Error occurred during deletion.");
            e.printStackTrace();
        }
        return deleted > 0;
    }

    public static boolean deleteProduct(int productID) throws ClassNotFoundException, SQLException
    {
        String sql = "DELETE FROM ProductDetails WHERE productID = ?";
        int deleted = 0;
        try
        {
            try (Connection connection = DatabaseConfig.getDBConnection();
                 PreparedStatement statement = connection.prepareStatement(sql))
                 {
                    statement.setInt(1, productID);
                    // executeUpdate() -> DELETE/INSERT/UPDATE
                    deleted = statement.executeUpdate(); // Deleting process produce integer(0 or 1) to check delete process
                 }
        } catch (ClassNotFoundException e) {
            // This catch block handles the error if the JAR file is missing from the classpath.
            System.err.println("Error: MySQL JDBC Driver not found. Please ensure the JAR is correctly configured.");
            e.printStackTrace();
        } catch (SQLException e) {
            // This catch block handles connection issues (wrong password, server down, etc.)
            System.err.println("Connection Failed or SQL Error occurred during deletion.");
            e.printStackTrace();
        }
        return deleted > 0;
    }
}
