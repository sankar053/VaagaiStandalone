package com.iii.VaagaiStandalone.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iii.VaagaiStandalone.config.DatabaseConfig;
import com.iii.VaagaiStandalone.dto.CategoryDto;
import com.iii.VaagaiStandalone.service.ICategory;
import com.iii.VaagaiStandalone.utils.QueryConstant;

public class CategoryService implements ICategory {

    @Override
    public boolean create(CategoryDto category) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConfig.getDBConnection();
            statement = connection.prepareStatement(QueryConstant.CATEGORY_INSERT);

            statement.setInt(1, category.getCategoryID());
            statement.setString(2, category.getCategoryName());

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error adding category : " + category.getCategoryID());
            e.printStackTrace(); // Good practice to see the actual error
            return false;
        } finally {
            // Explicitly closing resources to prevent memory leaks
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.err.println("Error closing database resources.");
            }
        }
    }

	@Override
	public boolean update(CategoryDto category) {
        Connection connection = null;
        PreparedStatement statement = null;
        int updated = 0;
        try
        {
            connection = DatabaseConfig.getDBConnection();
            statement = connection.prepareStatement(QueryConstant.CATEGORY_UPDATE);

            statement.setString(1, category.getCategoryName());
            statement.setInt(2, category.getCategoryID());
                // executeUpdate() -> DELETE/INSERT/UPDATE
            updated = statement.executeUpdate();  //Updating process produce integer(0 or 1)
            if (updated > 0)
            {
                System.out.println(" -> Successfully updated Category ID " + category.getCategoryID() + " to: " + category.getCategoryName());
            } else {
                System.out.println(" -> No category found with ID " + category.getCategoryID() + " to update.");
            }
        } catch (Exception e) {
            System.err.println("Error updating category : " + category.getCategoryID());
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.err.println("Error closing database resources.");
            }
        }
        return updated > 0;
    }

	@Override
    public boolean delete(CategoryDto categoryId) {
        Connection connection = null;
        PreparedStatement statement = null;
        int deleted = 0;

        try {
            connection = DatabaseConfig.getDBConnection();
            statement = connection.prepareStatement(QueryConstant.CATEGORY_DELETE);

            statement.setInt(1, categoryId.getCategoryID());
            deleted = statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            // Catching both expected exceptions and wrapping them
            throw new RuntimeException("Database error during deletion", e);
        } finally {
            // The finally block runs regardless of whether an exception was thrown
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Log closure failures but don't let them override the main logic
                System.err.println("Failed to close database resources: " + e.getMessage());
            }
        }
        return deleted > 0;
    }

	@Override
    public List<CategoryDto> getAll() {
        List<CategoryDto> categories = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConfig.getDBConnection();
            statement = connection.prepareStatement(QueryConstant.CATEGORY_SELECT);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CategoryDto category = new CategoryDto(
                        resultSet.getInt("categoryID"),
                        resultSet.getString("categoryName")
                );
                categories.add(category);
            }
            return categories;

        } catch (ClassNotFoundException e) {
            System.err.println("Error: MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection Failed! A SQL error occurred.");
            e.printStackTrace();
        } finally {
            // Resources are closed in reverse order of creation
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing database resources: " + e.getMessage());
            }
        }
        return null;
    }
}
