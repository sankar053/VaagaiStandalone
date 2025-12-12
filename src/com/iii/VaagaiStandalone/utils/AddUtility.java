package com.iii.VaagaiStandalone.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.iii.VaagaiStandalone.config.DatabaseConfig;

public class AddUtility
{
    

    public static boolean addCategory(int categoryID, String categoryName)
    {
        String query = "INSERT INTO Category(categoryID, categoryName) VALUES (?, ?)";
        try
        {
     
            try (Connection connection = DatabaseConfig.getDBConnection();
                 PreparedStatement statement = connection.prepareStatement(query))
                 {
                    statement.setInt(1, categoryID);
                    statement.setString(2, categoryName);
                     // executeUpdate() -> DELETE/INSERT/UPDATE
                    return statement.executeUpdate() > 0;  //executeUpdate() returns integer(0 or 1)
                 }
        } catch (Exception e) {
            System.err.println("Error adding category : " + categoryID);
            return false;
        }
    }

    public static boolean addProduct(int productID, int categoryID, String productName, String productImage, String productPackSize,String productAttribute, double actualPrice,double discountPrice, double rating) {
        // Insert into ProductDetails
        String productdetails = "INSERT INTO ProductDetails(productID, categoryID, productName, productImage, productPackSize, productAttribute) VALUES (?, ?, ?, ?, ?, ?)";
        // Insert into ProductPrice
        String productprice = "INSERT INTO ProductPrice(productID, productActualPrice, productDiscountPrice) VALUES (?, ?, ?)";
        // Insert into ProductRating
        String productrating = "INSERT INTO ProductRating(productID, productRating) VALUES (?, ?)";

        boolean added = false;
        try
        {
       
            try (Connection connection = DatabaseConfig.getDBConnection())
            {
                // 1. Insert Product Details
                try (PreparedStatement statement = connection.prepareStatement(productdetails))
                {
                    statement.setInt(1, productID);
                    statement.setInt(2, categoryID);
                    statement.setString(3, productName);
                    statement.setString(4, productImage);
                    statement.setString(5, productPackSize);
                    statement.setString(6, productAttribute);
                    // executeUpdate() -> DELETE/INSERT/UPDATE
                    added = statement.executeUpdate() > 0;  // The added boolean is set to true if executeUpdate() returns a value greater than 0, confirming the primary record was created.
                }

                // 2. Insert Product Price
                try (PreparedStatement statement = connection.prepareStatement(productprice))
                {
                    statement.setInt(1, productID);
                    statement.setDouble(2, actualPrice);
                    statement.setDouble(3, discountPrice);
                    statement.executeUpdate();
                }

                // 3. Insert Product Rating
                try (PreparedStatement statement = connection.prepareStatement(productrating))
                {
                    statement.setInt(1, productID);
                    statement.setDouble(2, rating);
                    statement.executeUpdate();
                }

                if (added)
                {
                    System.out.println(" -> Successfully added product: " + productName);
                }

            }
        } catch (Exception e) {
            System.err.println("Error adding product : " + productID );
            return false;
        }
        return added;
    }
}
