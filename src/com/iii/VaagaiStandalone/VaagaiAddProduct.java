package com.iii.VaagaiStandalone;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

import com.iii.VaagaiStandalone.dto.CategoryDto;
import com.iii.VaagaiStandalone.dto.ProductDetailsDto;
import com.iii.VaagaiStandalone.dto.ProductPriceDto;
import com.iii.VaagaiStandalone.dto.ProductRatingDto;
import com.iii.VaagaiStandalone.service.ICategory;
import com.iii.VaagaiStandalone.service.IProduct;
import com.iii.VaagaiStandalone.service.impl.CategoryService;
import com.iii.VaagaiStandalone.service.impl.ProductService;

public class VaagaiAddProduct {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add Category...");

        // ADD CATEGORIES based on user input
        int numCategories = 0;
        try {
            System.out.print("\nEnter the number of categories to add: ");
            numCategories = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.err.println("Invalid input. Please enter a number for the count.");
            scanner.close();
            return;
        }

        scanner.nextLine();

        for (int i = 0; i < numCategories; i++) {
            System.out.println("\nAdding Category " + (i + 1));

            // It's safer to let the user input the ID to avoid collision or use a DB
            // auto-increment feature.
            int categoryID = new Random().nextInt(10000); // Generate a random 4-digit ID
            System.out.println("Auto Generated Category ID: " + categoryID);

            System.out.print("Enter Category Name: ");
            String categoryName = scanner.nextLine();

            CategoryDto catData = new CategoryDto(categoryID, categoryName);

            ICategory categoryService = new CategoryService();

            boolean categoryadded = categoryService.create(catData);
            if (categoryadded) {
                System.out.println("Category added successfully to DB.");
            } else {
                System.out.println("Failed to add category " + categoryID + ". Check logs/constraints.");
            }
        }
        // Printing ALL Category list from DB
        ICategory categoryService = new CategoryService();
        List<CategoryDto> allCategories = categoryService.getAll();
        System.out.println("Loaded Category details...");

        Stream<CategoryDto> categoryStream = allCategories.stream();
        System.out.println("Category Details...");
        categoryStream.forEach(System.out::println);

//        List<ProductDetailsDto> addedProducts = new ArrayList<>();

        // --- 2. ADD PRODUCTS based on user input ---
        int numProducts = 0;
        try {
            System.out.print("\n>>> Enter the number of products to add: ");
            numProducts = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.err.println("Invalid input. Please enter a number for the count.");
            scanner.close();
            return;
        }

        scanner.nextLine(); // Consume the newline

        int categoryID;
        int productID;
        int productPriceID;
        int productRatingID;
        for (int i = 0; i < numProducts; i++) {
            System.out.println("\n--- Adding Product " + (i + 1) + " ---");

            productID = new Random().nextInt(10000);
            System.out.println("Auto Generated Product ID: " + productID);

            System.out.print("Enter Product Category ID : ");
            categoryID = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Product Name: ");
            String productName = scanner.nextLine();

            System.out.print("Enter Product Pack Size: ");
            String productPackSize = scanner.nextLine();

            String productImage = "Image of " + productName;

            System.out.print("Enter Product Attribute: ");
            String productAttribute = scanner.nextLine();

            System.out.print("Enter Product Price id : ");
            productPriceID = scanner.nextInt();
            System.out.print("Enter Actual Price: ");
            double actualPrice = scanner.nextDouble();
            System.out.print("Enter Discount Price: ");
            double discountPrice = scanner.nextDouble();
            System.out.print("Enter Product Rating id : ");
            productRatingID = scanner.nextInt();
            System.out.print("Enter Rating (e.g., 4.5): ");
            double rating = scanner.nextDouble();
            scanner.nextLine(); // Consume final newline

            // Create DTO Objects
            ProductDetailsDto detailsDto = new ProductDetailsDto(productID, productName, productPackSize, productImage, productAttribute,categoryID);
            ProductPriceDto priceDto = new ProductPriceDto(productPriceID, actualPrice, discountPrice);
            ProductRatingDto ratingDto = new ProductRatingDto(productRatingID, rating);

            IProduct productService = new ProductService();
            // Call the Create Method
            boolean success = productService.create(detailsDto,priceDto,ratingDto);
            if (success) {
                System.out.println("Product added successfully to DB.");
                System.out.println("\n--- All Products Loaded to DB ---");
            } else {
                System.out.println("Failed to add product " + productID);
            }
        }
        scanner.close();
    }
}