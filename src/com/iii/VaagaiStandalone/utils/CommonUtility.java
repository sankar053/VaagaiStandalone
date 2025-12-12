package com.iii.VaagaiStandalone.utils;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iii.VaagaiStandalone.config.DatabaseConfig;
import com.iii.VaagaiStandalone.dao.Category;
import com.iii.VaagaiStandalone.dao.ProductDetails;
import com.iii.VaagaiStandalone.dao.ProductPrice;
import com.iii.VaagaiStandalone.dao.ProductRating;

public class CommonUtility
{


    public static List<Category> loadCategoryInformation()
    {
        // Query to Execute
        String query = "SELECT * FROM category";
        List<Category> categories = new ArrayList<>();
        try
        {
            // Establish Connection and Execute Query
            try (Connection connection = DatabaseConfig.getDBConnection();
                 PreparedStatement statement = connection.prepareStatement(query);
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

    public static List<ProductDetails> loadProductInformation(int categoryID)
    {
        // 2. Query to Execute
        String query = "SELECT * FROM productdetails WHERE categoryID = ?";
        List<ProductDetails> productDetails = new ArrayList<>();
        try
        {
            // 3. Establish Connection and Execute Query
            try (Connection connection = DatabaseConfig.getDBConnection();
                 PreparedStatement statement = connection.prepareStatement(query))
                 {
                    statement.setInt(1, categoryID);
                    ResultSet resultSet = statement.executeQuery();
                    {
                        System.out.println("Connected to the Database Successfully!");
                        // 4. Process the Result
                        while (resultSet.next())
                        {
                            ProductDetails product = new ProductDetails(resultSet.getInt("productID"),
                                    resultSet.getString("productName"),
                                    resultSet.getString("productImage"),
                                    resultSet.getString("productPackSize"),
                                    resultSet.getString("productAttribute"),
                                    loadProductPriceInformation(resultSet.getInt("productID")), // productActualPrice
                                    loadProductPriceInformation(resultSet.getInt("productID")), //productDiscountPrice
                                    loadProductRatingInformation(resultSet.getInt("productID")));  // productRating
                            productDetails.add(product);
                        }
                    }
                }
            return productDetails;
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

    public static ProductPrice loadProductPriceInformation(int productID)
    {
        // Query to Execute
        String query = "SELECT productActualPrice,productDiscountPrice FROM productprice WHERE productID = ?";
        try
        {
            //  Establish Connection and Execute Query
            try (Connection connection = DatabaseConfig.getDBConnection();
                 PreparedStatement statement = connection.prepareStatement(query))
                 {
                    statement.setInt(1, productID);
                    ResultSet resultSet = statement.executeQuery();
                    {
                        System.out.println("Connected to the Database Successfully!");

                        while (resultSet.next()) {
                            ProductPrice productprice = new ProductPrice(resultSet.getDouble("productActualPrice"),
                                    resultSet.getDouble("productDiscountPrice"));
                            return productprice;
                        }
                    }
                 }
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

    public static ProductRating loadProductRatingInformation(int productID)
    {
        // Query to Execute
        String query = "SELECT productRating FROM productrating WHERE productID = ? ";
        try {
            // Establish Connection and Execute Query
            try (Connection connection = DatabaseConfig.getDBConnection();
                 PreparedStatement statement = connection.prepareStatement(query))
                 {
                    statement.setInt(1, productID);
                    ResultSet resultSet = statement.executeQuery();
                    {
                        System.out.println("Connected to the Database Successfully!");
                        while (resultSet.next())
                        {
                            ProductRating productrating = new ProductRating(resultSet.getDouble("productRating"));
                            return productrating;
                        }
                    }
                 }
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

    public static boolean isProductAvailable(int categoryID, int productID)
    {
        //COUNT(*) - return the Count of number of rows in Product details
        String query = "SELECT COUNT(*) FROM ProductDetails WHERE categoryID = ? AND productID = ?";
        boolean productAvailable = false;
        try
        {
            // Establish Connection and Execute Query
            try (Connection connection = DatabaseConfig.getDBConnection();
                 PreparedStatement statement = connection.prepareStatement(query))
                 {
                    // Set two parameters for ?,?
                    statement.setInt(1, categoryID);
                    statement.setInt(2, productID);
                    try (ResultSet resultSet = statement.executeQuery())  // returns no of rows (COUNT(*))
                    {
                        // Process the Product Availability
                        if (resultSet.next())
                        {
                        // COUNT(*) returns the number of rows (as column 1). eg.10 rows
                        // check if the count is greater than 0.
                            if (resultSet.getInt(1) > 0)  // Retrieves the count value from the first column.
                            {
                                productAvailable = true;
                            }
                        }
                    }
                }
        } catch (ClassNotFoundException e) {
            // This catch block handles the error if the JAR file is missing from the classpath.
            System.err.println("Error: MySQL JDBC Driver not found. Please ensure the JAR is correctly configured.");
        } catch (SQLException e) {
            // This catch block handles connection issues (wrong password, server down, etc.)
            System.err.println("Connection Failed! A SQL error occurred.");
        }

        return productAvailable;
    }

    public static boolean fetchAndPrintProductDetails(int categoryID, int productID)
    {
        // Query to Execute
        String query = "SELECT productID, productName, productImage, productPackSize, productAttribute " +
                "FROM ProductDetails WHERE productID = ? AND categoryID = ?";
        try
        {
            try (Connection connection = DatabaseConfig.getDBConnection();
                 PreparedStatement statement = connection.prepareStatement(query))
                 {
                    // Set the parameters for ?,?
                    statement.setInt(1, productID);
                    statement.setInt(2, categoryID);

                    try (ResultSet resultSet = statement.executeQuery())
                    {
                        if (resultSet.next())
                        {
                            // fetch price/rating and print if product available.
                            System.out.printf("\nProduct ID %d found in Category ID %d. Full Details:%n", productID, categoryID);

                            // Fetch Price and Rating details
                            ProductPrice productActualPrice = loadProductPriceInformation(productID);
                            ProductPrice productDiscountPrice = loadProductPriceInformation(productID);
                            ProductRating rating = loadProductRatingInformation(productID);

                            // Print Details
                            ProductDetails details = new ProductDetails(resultSet.getInt("productID"),
                                    resultSet.getString("productName"),
                                    resultSet.getString("productImage"),
                                    resultSet.getString("productPackSize"),
                                    resultSet.getString("productAttribute"),
                                    productActualPrice,productDiscountPrice,rating);  // productID
                            System.out.println(details.toString());
                            return true;
                        } else {
                        // Product is NOT AVAILABLE or does not belong to the category
                        System.out.printf("\nProduct ID %d was NOT found in Category ID %d.%n", productID, categoryID);
                        return false;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            // This catch block handles the error if the JAR file is missing from the classpath.
            System.err.println("Error: MySQL JDBC Driver not found. Please ensure the JAR is correctly configured.");
        } catch (SQLException e) {
            // This catch block handles connection issues (wrong password, server down, etc.)
            System.err.println("Connection Failed! A SQL error occurred.");
        }
        return false;
    }
}
