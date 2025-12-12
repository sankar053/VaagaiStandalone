package com.iii.VaagaiStandalone01.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.iii.VaagaiStandalone01.config.DatabaseConfig;

public class UpdateUtility
{
   
    public static boolean updateCategory(int categoryID, String newCategoryName)
    {
        String sql = "UPDATE Category SET categoryName = ? WHERE categoryID = ?";
        int updated = 0;
        try
        {
            try (Connection connection = DatabaseConfig.getDBConnection();
                 PreparedStatement statement = connection.prepareStatement(sql))
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

    public static boolean updateProductDetails(int productID, String newProductName, String newProductImage, String newProductPackSize, String newProductAttribute, double newActualPrice, double newRating)
    {
        // 1. UPDATE ProductDetails
        String productdetails = "UPDATE ProductDetails SET productName = ?, productImage = ?, productPackSize = ?, productAttribute = ? WHERE productID = ?";
        // 2. UPDATE ProductPrice
        String productactualprice = "UPDATE ProductPrice SET productActualPrice = ? WHERE productID = ?";
        // 3. UPDATE ProductRating
        String productrating = "UPDATE ProductRating SET productRating = ? WHERE productID = ?";

        int updated = 0;
        try
        {
            try (Connection connection = DatabaseConfig.getDBConnection())
            {
                // Update Product Details Table
                try (PreparedStatement statement = connection.prepareStatement(productdetails))
                {
                    statement.setString(1, newProductName);
                    statement.setString(2, newProductImage);
                    statement.setString(3, newProductPackSize);
                    statement.setString(4, newProductAttribute);
                    statement.setInt(5, productID);
                    // executeUpdate() -> DELETE/INSERT/UPDATE
                    updated = statement.executeUpdate(); // Updating process produce integer(0 or 1) to check update status
                }

                // Update Product Price Table
                try (PreparedStatement statement = connection.prepareStatement(productactualprice))
                {
                    statement.setDouble(1, newActualPrice);
                    statement.setInt(2, productID);
                    statement.executeUpdate(); // return(0 or 1) to chack update
                }

                // Update Product Rating Table
                try (PreparedStatement statement = connection.prepareStatement(productrating))
                {
                    statement.setDouble(1, newRating);
                    statement.setInt(2, productID);
                    // executeUpdate() -> DELETE/INSERT/UPDATE
                    statement.executeUpdate(); // return(0 or 1) to chack update
                }

                if (updated > 0)
                {
                    System.out.println(" -> Successfully updated all details for Product ID: " + productID);
                } else {
                    System.out.println(" -> No product found with ID " + productID + " to update.");
                }

            }
        } catch (Exception e) {
            System.err.println("Error updating product : " + productID);
            return false;
        }
        return updated > 0;
    }
}
