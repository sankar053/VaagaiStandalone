package com.iii.VaagaiStandalone;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

import com.iii.VaagaiStandalone.dto.CategoryDto;
import com.iii.VaagaiStandalone.dto.ProductDetailsDto;
import com.iii.VaagaiStandalone.service.ICategory;
import com.iii.VaagaiStandalone.service.IProduct;
import com.iii.VaagaiStandalone.service.impl.CategoryService;
import com.iii.VaagaiStandalone.service.impl.ProductService;

public class VaagaiDeleteProduct
{
    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        IProduct productService = new ProductService();
        int productIdToDelete = 6881;
        System.out.println("Product ID to delete : " + productIdToDelete);

        ProductDetailsDto productID = new ProductDetailsDto(productIdToDelete);

        boolean deletedproduct = productService.delete(productID);
        if (deletedproduct) {
            System.out.println("Product ID  " + productIdToDelete + " was successfully deleted.");
        }
        else
        {
            System.out.println("Failed to delete Product ID " + productIdToDelete);
        }

        ICategory categoryService = new CategoryService();
        int categoryIdToDelete = 9442;

        System.out.println("Category ID to delete : " + categoryIdToDelete);
        CategoryDto categoryID = new CategoryDto(categoryIdToDelete);

        boolean deletecategory = categoryService.delete(categoryID); //return 1 if deleted ,0 if not deleted

        if (deletecategory) {
            System.out.println("Category ID  " + categoryIdToDelete + " was successfully deleted.");
        } else {
            System.out.println(" -> No product found with ID " + categoryIdToDelete + " to delete.");
        }
        List<CategoryDto> allCategories = categoryService.getAll();
        System.out.println("Loaded Category details...");
        System.out.println("Category Details...");

        Stream<CategoryDto> categoryStream = allCategories.stream();
        categoryStream.forEach(System.out::println);
    }
}
