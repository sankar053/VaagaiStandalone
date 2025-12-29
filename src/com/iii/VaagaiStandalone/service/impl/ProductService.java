package com.iii.VaagaiStandalone.service.impl;

import com.iii.VaagaiStandalone.config.DatabaseConfig;
import com.iii.VaagaiStandalone.dto.CategoryDto;
import com.iii.VaagaiStandalone.dto.ProductDetailsDto;
import com.iii.VaagaiStandalone.dto.ProductPriceDto;
import com.iii.VaagaiStandalone.dto.ProductRatingDto;
import com.iii.VaagaiStandalone.service.IProduct;
import com.iii.VaagaiStandalone.utils.QueryConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProduct
{
    @Override
    public boolean create(ProductDetailsDto productDetails, ProductPriceDto productPrice, ProductRatingDto productRating) {
        Connection connection = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        PreparedStatement statement3 = null;
        boolean success = false;

        try {
            connection = DatabaseConfig.getDBConnection();

            // 1. Insert Product Details
            statement1 = connection.prepareStatement(QueryConstant.PRODUCTDETAILS_INSERT);
            statement1.setInt(1, productDetails.getProductID());
            statement1.setString(2, productDetails.getProductName());
            statement1.setString(3, productDetails.getProductPackSize());
            statement1.setString(4, productDetails.getProductImage());
            statement1.setString(5, productDetails.getProductAttribute());
            statement1.setInt(6, productDetails.getCategoryID());
            statement1.executeUpdate();

            // 2. Insert Product Price
            statement2 = connection.prepareStatement(QueryConstant.PRODUCTPRICE_INSERT);
            statement2.setInt(1, productPrice.getProductPriceID());
            statement2.setDouble(2, productPrice.getProductActualPrice());
            statement2.setDouble(3, productPrice.getProductDiscountPrice());
            statement2.setInt(4, productDetails.getProductID());
            statement2.executeUpdate();

            // 3. Insert Product Rating
            statement3 = connection.prepareStatement(QueryConstant.PRODUCTRATING_INSERT);
            statement3.setInt(1, productRating.getProductRatingID());
            statement3.setDouble(2, productRating.getProductRating());
            statement3.setInt(3, productDetails.getProductID());

            success = statement3.executeUpdate() > 0;

            if (success) {
                System.out.println(" -> Successfully added product: " + productDetails.getProductName());
            }

        } catch (Exception e) {
            System.err.println("Error adding product : " + productDetails.getProductID());
            e.printStackTrace();
            return false;
        } finally {
            // Close each resource individually to ensure all close attempts are made
            try {
                if (statement1 != null) statement1.close();
                if (statement2 != null) statement2.close();
                if (statement3 != null) statement3.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return success;
    }

    @Override
    public boolean update(ProductDetailsDto productdetails, ProductPriceDto productprice, ProductRatingDto productRating) {
        Connection connection = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        PreparedStatement statement3 = null;
        int updated = 0;

        try {
            connection = DatabaseConfig.getDBConnection();

            // 1. Update Product Details Table
            statement1 = connection.prepareStatement(QueryConstant.PRODUCTDETAILS_UPDATE);
            statement1.setInt(1, productdetails.getProductID());
            statement1.setString(2, productdetails.getProductName());
            statement1.setString(3, productdetails.getProductPackSize());
            statement1.setString(4, productdetails.getProductImage());
            statement1.setString(5, productdetails.getProductAttribute());
            statement1.setInt(6, productdetails.getCategoryID());
            updated = statement1.executeUpdate();

            // 2. Update Product Price Table
            statement2 = connection.prepareStatement(QueryConstant.PRODUCTPRICE_UPDATE);
            statement2.setInt(1, productprice.getProductPriceID());
            statement2.setDouble(2, productprice.getProductActualPrice());
            statement2.setDouble(3, productprice.getProductDiscountPrice());
            statement2.setInt(4, productdetails.getProductID());
            statement2.executeUpdate();

            // 3. Update Product Rating Table
            statement3 = connection.prepareStatement(QueryConstant.PRODUCTRATING_UPDATE);
            statement3.setInt(1, productRating.getProductRatingID());
            statement3.setDouble(2, productRating.getProductRating());
            statement3.setInt(3, productdetails.getProductID());
            statement3.executeUpdate();

            if (updated > 0) {
                System.out.println(" -> Successfully updated all details for Product ID: " + productdetails.getProductID());
            } else {
                System.out.println(" -> No product found with ID " + productdetails.getProductID() + " to update.");
            }

        } catch (Exception e) {
            System.err.println("Error updating product : " + productdetails.getProductID());
            e.printStackTrace();
            return false;
        } finally {
            // Explicitly closing each resource
            try {
                if (statement1 != null) statement1.close();
                if (statement2 != null) statement2.close();
                if (statement3 != null) statement3.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing database resources: " + e.getMessage());
            }
        }
        return updated > 0;
    }

    @Override
    public boolean delete(ProductDetailsDto productID) {
        Connection connection = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        PreparedStatement statement3 = null;
        int deleted = 0;

        try {
            connection = DatabaseConfig.getDBConnection();

            // 1. Delete from Product Rating Table
            statement1 = connection.prepareStatement(QueryConstant.PRODUCTRATING_DELETE);
            statement1.setInt(1, productID.getProductID());
            statement1.executeUpdate();

            // 2. Delete from Product Price Table
            statement2 = connection.prepareStatement(QueryConstant.PRODUCTPRICE_DELETE);
            statement2.setInt(1, productID.getProductID());
            statement2.executeUpdate();

            // 3. Delete from Product Details Table
            statement3 = connection.prepareStatement(QueryConstant.PRODUCTDETAILS_DELETE);
            statement3.setInt(1, productID.getProductID());
            deleted = statement3.executeUpdate();

            if (deleted > 0) {
                System.out.println(" -> Product Details were deleted Successfully ");
            } else {
                System.out.println(" -> No product found with ID " + productID.getProductID() + " to delete.");
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Error: MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection Failed or SQL Error occurred during deletion.");
            e.printStackTrace();
        } finally {
            // Closing resources in reverse order to ensure a clean shutdown
            try {
                if (statement1 != null) statement1.close();
                if (statement2 != null) statement2.close();
                if (statement3 != null) statement3.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing database resources: " + e.getMessage());
            }
        }
        return deleted > 0;
    }

    @Override
    public List<ProductDetailsDto> getAll(CategoryDto categoryId) {
        List<ProductDetailsDto> productDetails = new ArrayList<>();

        // Resource declarations
        Connection connection = null;
        PreparedStatement stmtMain = null;
        PreparedStatement stmtPrice = null;
        PreparedStatement stmtRating = null;
        ResultSet rsMain = null;
        ResultSet rsPrice = null;
        ResultSet rsRating = null;

        try {
            connection = DatabaseConfig.getDBConnection();
            stmtMain = connection.prepareStatement(QueryConstant.PRODUCTDETAILS_SELECT);
            stmtMain.setInt(1, categoryId.getCategoryID());
            rsMain = stmtMain.executeQuery();

            while (rsMain.next()) {
                int currentID = rsMain.getInt("productID");

                // Fetch Price
                stmtPrice = connection.prepareStatement(QueryConstant.PRODUCTPRICE_SELECT);
                stmtPrice.setInt(1, currentID);
                rsPrice = stmtPrice.executeQuery();
                ProductPriceDto actualprice = null;
                if (rsPrice.next()) {
                    actualprice = new ProductPriceDto(
                            rsPrice.getInt("id"),
                            rsPrice.getDouble("productActualPrice"),
                            rsPrice.getDouble("productDiscountPrice"));
                }
                // Close nested result set/statement immediately to reuse variables in loop
                rsPrice.close();
                stmtPrice.close();

                // Fetch Rating
                stmtRating = connection.prepareStatement(QueryConstant.PRODUCTRATING_SELECT);
                stmtRating.setInt(1, currentID);
                rsRating = stmtRating.executeQuery();
                ProductRatingDto rating = null;
                if (rsRating.next()) {
                    rating = new ProductRatingDto(rsRating.getInt("id"),
                            rsRating.getDouble("productRating"));
                }
                rsRating.close();
                stmtRating.close();

                ProductDetailsDto product = new ProductDetailsDto(
                        currentID,
                        rsMain.getString("productName"),
                        rsMain.getString("productPackSize"),
                        rsMain.getString("productImage"),
                        rsMain.getString("productAttribute"),
                        actualprice, actualprice, rating);

                productDetails.add(product);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Database error occurred while fetching products.");
            e.printStackTrace();
        } finally {
            // Final cleanup of all resources
            try {
                if (rsMain != null) rsMain.close();
                if (rsPrice != null) rsPrice.close();
                if (rsRating != null) rsRating.close();
                if (stmtMain != null) stmtMain.close();
                if (stmtPrice != null) stmtPrice.close();
                if (stmtRating != null) stmtRating.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }

            // Your existing log logic
            if (productDetails.isEmpty()) {
                System.out.println("Operation finished: No products found.");
            } else {
                System.out.println("Operation finished: Successfully retrieved " + productDetails.size() + " products.");
            }
        }
        return productDetails;
    }
}
