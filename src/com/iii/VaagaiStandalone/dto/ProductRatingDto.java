package com.iii.VaagaiStandalone.dto;

public class ProductRatingDto
{
    private int productRatingID;
    private double productRating;

    public ProductRatingDto(int productRatingID, double productRating) {
        this.productRatingID = productRatingID;
        this.productRating = productRating;
    }

    public int getProductRatingID() {
        return productRatingID;
    }

    public double getProductRating() {
        return productRating;
    }

    public double prodrating()
    {
        return productRating;
    }

}
