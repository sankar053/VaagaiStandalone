package com.iii.VaagaiStandalone;

import java.util.List;
import java.util.Scanner;

import com.iii.VaagaiStandalone.dto.CategoryDto;
import com.iii.VaagaiStandalone.dto.ProductDetailsDto;
import com.iii.VaagaiStandalone.service.ICategory;
import com.iii.VaagaiStandalone.service.IProduct;
import com.iii.VaagaiStandalone.service.impl.CategoryService;
import com.iii.VaagaiStandalone.service.impl.ProductService;

public class VaagaiDBMain
{
    public static void main(String[] args) {
        System.out.println("--- Loaded Category and Product details ---");
        System.out.println("Category Details...");
        ICategory categoryService = new CategoryService();
        List<CategoryDto> category = categoryService.getAll();
        category.stream().forEach(System.out::println);
        // Category ID Input from User
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Product Availability Check ");

        // Category ID from user
        System.out.print("Enter the Category ID: ");
        int categoryId = 0;
        if (scanner.hasNextInt()) {
            categoryId = scanner.nextInt();
        } else {
            System.out.println("Invalid input for Category ID.");
        }

        IProduct productService = new ProductService();
        CategoryDto categoryID = new CategoryDto(categoryId);
        List<ProductDetailsDto> product = productService.getAll(categoryID);
        System.out.println("Product Details...");
        for (ProductDetailsDto productDetails : product) {
            System.out.println(productDetails.productdetails());
        }
    }
}
