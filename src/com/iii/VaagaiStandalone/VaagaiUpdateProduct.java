package com.iii.VaagaiStandalone;

import java.util.List;
import java.util.stream.Stream;

import com.iii.VaagaiStandalone.dto.CategoryDto;
import com.iii.VaagaiStandalone.dto.ProductDetailsDto;
import com.iii.VaagaiStandalone.dto.ProductPriceDto;
import com.iii.VaagaiStandalone.dto.ProductRatingDto;
import com.iii.VaagaiStandalone.service.ICategory;
import com.iii.VaagaiStandalone.service.IProduct;
import com.iii.VaagaiStandalone.service.impl.CategoryService;
import com.iii.VaagaiStandalone.service.impl.ProductService;

public class VaagaiUpdateProduct
{
    public static void main(String[] args)
    {
        System.out.println("Category Update...");

        int categoryIdToUpdate = 9442;
        String newCategoryName = "Chocolate Varieties";

        CategoryDto catData = new CategoryDto(categoryIdToUpdate, newCategoryName);

        ICategory categoryService = new CategoryService();

        System.out.println("Updating Category ID : " + categoryIdToUpdate);

        boolean categoryUpdateSuccess = categoryService.update(catData);

        if (categoryUpdateSuccess) {
            System.out.println("Category updated successfully!.");
        } else {
            System.out.println("Category update failed or category did not exist.");
        }
        List<CategoryDto> allCategories = categoryService.getAll();
        System.out.println("Loaded Category details...");
        System.out.println("Category Details...");

        Stream<CategoryDto> categoryStream = allCategories.stream();
        categoryStream.forEach(System.out::println);

        System.out.println("Product Update...");
        int productIdToUpdate = 6881;

        // Updating Product details
        String newProductName = "Multi-Layered 5 Star Chocolate";
        String newProductPackSize = "50g";
        String newProductImage = "Image of 5 Star";
        String newProductAttribute = "Unique multi-layered structure";
        int newProductPriceID = 11;
        double newActualPrice = 600.00;
        double newDiscountPrice = 500.00;
        int newProductRatingID = 11;
        double newRating = 4.9;

        System.out.println("Updating Product ID " + productIdToUpdate);

        IProduct productService = new ProductService();

        ProductDetailsDto newdetailsDto = new ProductDetailsDto(productIdToUpdate, newProductName, newProductPackSize, newProductImage, newProductAttribute,categoryIdToUpdate);
        ProductPriceDto newpriceDto = new ProductPriceDto(newProductPriceID, newActualPrice, newDiscountPrice);
        ProductRatingDto newratingDto = new ProductRatingDto(newProductRatingID, newRating);

        boolean productUpdateSuccess = productService.update(newdetailsDto, newpriceDto,  newratingDto);

        if (productUpdateSuccess) {
            System.out.println("Product details updated successfully!");
        } else {
            System.out.println("Product details update failed or product did not exist.");
        }
    }
}
