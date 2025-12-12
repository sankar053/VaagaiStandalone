package com.iii.VaagaiStandalone;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.iii.VaagaiStandalone.dao.Category;
import com.iii.VaagaiStandalone.utils.AddUtility;
import com.iii.VaagaiStandalone.utils.CommonUtility;

public class VaagaiAddProduct
{
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
            System.out.println("\nAdding Category " + (i + 1) );

            // It's safer to let the user input the ID to avoid collision or use a DB auto-increment feature.
            int categoryID = new Random().nextInt(10000); // Generate a random 4-digit ID
            System.out.println("Auto Generated Category ID: " + categoryID);

            System.out.print("Enter Category Name: ");
            String categoryName = scanner.nextLine();

            boolean categoryadded = AddUtility.addCategory(categoryID, categoryName);
            if (categoryadded) {
                System.out.println("Category added successfully to DB.");
            } else {
                System.out.println("Failed to add category " + categoryID + ". Check logs/constraints.");
            }
        }
        // Printing ALL Category list from DB
        System.out.println("\nLoaded Category Details...");
        List<Category> allCategories = CommonUtility.loadCategoryInformation();
        if (allCategories != null) {
            for (Category cat : allCategories) {
                System.out.println("  > " + cat.mycat());
            }
        }

        // ADD PRODUCTS based on user input
        int numProducts = 0;
        try {
            System.out.print("\nEnter the number of products to add: ");
            numProducts = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.err.println("Invalid input. Please enter a number for the count.");
            scanner.close();
            return;
        }

        scanner.nextLine();

        int catID = 0;
        int productID = 0;
        for (int i = 0; i < numProducts; i++) {
            System.out.println("\nAdding Product " + (i + 1));

            productID = new Random().nextInt(10000);
            System.out.println("Auto Generated Product ID: " + productID);

            System.out.print("Enter Product Category ID : ");
            catID = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Product Name: ");
            String productName = scanner.nextLine();

            String productImage = "Image of " + productName;

            String productPackSize = scanner.nextLine();
            System.out.print("Enter Product Pack Size: ");
            String productAttribute = scanner.nextLine();
            System.out.print("Enter Product Attribute: ");

            System.out.print("Enter Actual Price: ");
            double actualPrice = scanner.nextDouble();
            System.out.print("Enter Discount Price: ");
            double discountPrice = scanner.nextDouble();
            System.out.print("Enter Rating (e.g., 4.5): ");
            double rating = scanner.nextDouble();
            scanner.nextLine();

            boolean productadded = AddUtility.addProduct(productID, catID, productName, productImage, productPackSize, productAttribute, actualPrice, discountPrice, rating);
            if (productadded) {
                System.out.println("Product added successfully to DB.");
            } else {
                System.out.println("Failed to add product " + productID);
            }
        }
        scanner.close();

        System.out.println("\nLoaded Product Details...");
        CommonUtility.fetchAndPrintProductDetails(catID, productID);
    }
}
