package com.iii.VaagaiStandalone;

import java.util.List;
import java.util.Scanner;

import com.iii.VaagaiStandalone.dao.Category;
import com.iii.VaagaiStandalone.dao.ProductDetails;
import com.iii.VaagaiStandalone.utils.CommonUtility;

public class VaagaiDBMain
{
    public static void main(String[] args)
    {
        System.out.println("Loaded Category and Product details...");
        System.out.println("Category Details...");
        List<Category> category = CommonUtility.loadCategoryInformation();
        for (Category cat : category) {
            System.out.println("  > " + cat.mycat());
        }
        // Category ID Input from User
        Scanner scanner = new Scanner(System.in);

        System.out.println("Product Availability Check ");

        // Category ID from user
        System.out.print("Enter the Category ID: ");
        int categoryId = 0;
        if (scanner.hasNextInt()) {
            categoryId = scanner.nextInt();
        } else {
            System.out.println("Invalid input for Category ID.");
            return;
        }

        List<ProductDetails> product = CommonUtility.loadProductInformation(categoryId);
        System.out.println("Product Details...");
        for(ProductDetails prod : product) {
            System.out.println(prod.myproduct());
        }
        // Product ID from user
        System.out.print("Enter the Product ID: ");
        int productId = 0;
        if (scanner.hasNextInt()) {
            productId = scanner.nextInt();
        } else {
            System.out.println("Invalid input for Product ID.");
            return;
        }
        scanner.close();

        // Check availability
        boolean available = CommonUtility.isProductAvailable(categoryId, productId);

        // 4. Print the result
        if (available) {
            System.out.printf("\nProduct ID %d is AVAILABLE in Category ID %d.\n", productId, categoryId);
            CommonUtility.fetchAndPrintProductDetails(categoryId, productId);
        } else {
            System.out.printf("\nProduct ID %d is NOT available or does not belong to Category ID %d.\n", productId, categoryId);
        }

        System.out.println("\nSearching product...");

        //CommonUtility.fetchAndPrintProductDetails(categoryId, productId);
    }
}
