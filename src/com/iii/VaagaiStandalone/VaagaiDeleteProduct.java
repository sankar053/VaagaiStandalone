package com.iii.VaagaiStandalone;

import java.sql.SQLException;
import java.util.List;

import com.iii.VaagaiStandalone.dao.Category;
import com.iii.VaagaiStandalone.utils.CommonUtility;
import com.iii.VaagaiStandalone.utils.DeleteUtility;

public class VaagaiDeleteProduct
{
    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        int categoryIdToDelete = 0;

        System.out.println("Category ID to delete : " + categoryIdToDelete);

        boolean categoryDeleted = DeleteUtility.deleteCategory(categoryIdToDelete);  //return 1 if deleted ,0 if not deleted

        if (categoryDeleted) {
            System.out.println("Category ID  " + categoryIdToDelete + " was successfully deleted.");
        } else {
            System.out.println("Failed to delete Category ID " + categoryIdToDelete);
        }
        List<Category> category = CommonUtility.loadCategoryInformation();
        for(Category cat : category) {
            System.out.println(cat.toString());
        }

        int productIdToDelete = 5836;

        System.out.println("Product ID to delete : " + productIdToDelete);

        boolean productDeleted = DeleteUtility.deleteProduct(productIdToDelete);  //return 1 if deleted ,0 if not deleted

        if (productDeleted) {
            System.out.println("Product ID " + productIdToDelete + " was successfully deleted.");
        } else {
            System.out.println("Failed to delete Product ID " + productIdToDelete);
        }
    }
}
