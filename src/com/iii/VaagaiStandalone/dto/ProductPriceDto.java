package com.iii.VaagaiStandalone.dto;

public class ProductPriceDto
{
    private int productPriceID;
    private double productActualPrice;
    private double productDiscountPrice;

    public ProductPriceDto(int productPriceID, double productActualPrice, double productDiscountPrice) {
        this.productPriceID = productPriceID;
        this.productActualPrice = productActualPrice;
        this.productDiscountPrice = productDiscountPrice;
    }

    public int getProductPriceID() {
        return productPriceID;
    }

    public double getProductActualPrice() {
        return productActualPrice;
    }

    public double getProductDiscountPrice() {
        return productDiscountPrice;
    }

    public double prodActualprice()
    {
        return productActualPrice;
    }
    public double prodDiscountprice()
    {
        return productDiscountPrice;
    }

}
