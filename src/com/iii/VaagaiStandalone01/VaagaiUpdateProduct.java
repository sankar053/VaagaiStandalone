package com.iii.VaagaiStandalone01;

import java.util.List;

import com.iii.VaagaiStandalone01.dao.Category;
import com.iii.VaagaiStandalone01.utils.CommonUtility;
import com.iii.VaagaiStandalone01.utils.UpdateUtility;

public class VaagaiUpdateProduct
{
    public static void main(String[] args)
    {
        System.out.println("Category Update...");

        int categoryIdToUpdate = 6;
        String newCategoryName = "Chocolate Varieties";

        System.out.println("Updating Category ID : " + categoryIdToUpdate);

        boolean categoryUpdateSuccess = UpdateUtility.updateCategory(categoryIdToUpdate, newCategoryName);

        if (categoryUpdateSuccess) {
            System.out.println("Category updated successfully!.");
        } else {
            System.out.println("Category update failed or category did not exist.");
        }

        System.out.println("Product Update...");
        int productIdToUpdate = 1; // dairy milk

        // Updating Product details
        String newProductName = "Multi-Layered 5 Star Chocolate";
        String newProductImage = "Image of 5 Star";
        String newProductPackSize = "50g";
        String newProductAttribute = "Unique multi-layered structure";
        double newActualPrice = 600.00;
        double newRating = 4.9;

        System.out.println("Updating Product ID " + productIdToUpdate);

        boolean productUpdateSuccess = UpdateUtility.updateProductDetails(
                productIdToUpdate,
                newProductName,
                newProductImage,
                newProductPackSize,
                newProductAttribute,
                newActualPrice,
                newRating );

        if (productUpdateSuccess) {
            System.out.println("Product details updated successfully!");
        } else {
            System.out.println("Product details update failed or product did not exist.");
        }
        List<Category> category = CommonUtility.loadCategoryInformation();
        for (Category cat : category) {
            System.out.println(cat.mycat());
        }
        CommonUtility.fetchAndPrintProductDetails(6, 1);
    }
}
