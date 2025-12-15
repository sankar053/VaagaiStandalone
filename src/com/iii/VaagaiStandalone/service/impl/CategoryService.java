package com.iii.VaagaiStandalone.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.iii.VaagaiStandalone.config.DatabaseConfig;
import com.iii.VaagaiStandalone.dto.CategoryDto;
import com.iii.VaagaiStandalone.service.ICategory;
import com.iii.VaagaiStandalone.utils.QueryConstant;

public class CategoryService implements ICategory {

	@Override
	public boolean create(CategoryDto category) {
	
		try {
			try (Connection connection = DatabaseConfig.getDBConnection();
					PreparedStatement statement = connection.prepareStatement(QueryConstant.CATEGORY_INSERT)) {
				statement.setInt(1, category.getCategoryId());
				statement.setString(2, category.getCategoryName());
				// executeUpdate() -> DELETE/INSERT/UPDATE
				return statement.executeUpdate() > 0; // executeUpdate() returns integer(0 or 1)
			}
		} catch (Exception e) {
			System.err.println("Error adding category : " + category.getCategoryId());
			return false;
		}
	}

	@Override
	public int update(CategoryDto category) {
        int updated = 0;
        try
        {
            try (Connection connection = DatabaseConfig.getDBConnection();
                 PreparedStatement statement = connection.prepareStatement(QueryConstant.CATEGORY_UPDATE))
            {
                statement.setString(1, newCategoryName);
                statement.setInt(2, categoryID);
                // executeUpdate() -> DELETE/INSERT/UPDATE
                updated = statement.executeUpdate();  //Updating process produce integer(0 or 1)
                if (updated > 0)
                {
                    System.out.println(" -> Successfully updated Category ID " + categoryID + " to: " + newCategoryName);
                } else {
                    System.out.println(" -> No category found with ID " + categoryID + " to update.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error updating category : " + categoryID);
        }
        return updated > 0;
    }

	@Override
	public int delete(int categoryId) {
        int deleted = 0;
        try
        {
            try (Connection connection = DatabaseConfig.getDBConnection();
                 PreparedStatement statement = connection.prepareStatement(QueryConstant.CATEGORY_DELETE))
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

	@Override
	public List<CategoryDto> getAll() {
        // Query to Execute
        List<Category> categories = new ArrayList<>();
        try
        {
            // Establish Connection and Execute Query
            try (Connection connection = DatabaseConfig.getDBConnection();
                 PreparedStatement statement = connection.prepareStatement(QueryConstant.CATEGORY_SELECT);
                 ResultSet resultSet = statement.executeQuery())
            {
                System.out.println("Connected to the Database Successfully!");
                // 4. Process the Result
                while (resultSet.next())
                {
                    List<ProductDetails> productDetails = loadProductInformation(resultSet.getInt("categoryID"));
                    Category category = new Category(resultSet.getInt("categoryID"),
                            resultSet.getString("categoryName"),
                            productDetails);
                    categories.add(category);
                }
            }
            return categories;
        } catch (ClassNotFoundException e) {
            // This catch block handles the error if the JAR file is missing from the classpath.
            System.err.println("Error: MySQL JDBC Driver not found. Please ensure the JAR is correctly configured.");
            e.printStackTrace();
        } catch (SQLException e) {
            // This catch block handles connection issues (wrong password, server down, etc.)
            System.err.println("Connection Failed! A SQL error occurred.");
            e.printStackTrace();
        }
        return null;
    }
}
