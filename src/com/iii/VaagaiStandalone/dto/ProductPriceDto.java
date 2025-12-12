package com.iii.VaagaiStandalone.dto;

public class ProductPriceDto
{
    private double productActualPrice;
    private double productDiscountPrice;

    public ProductPriceDto(double productActualPrice, double productDiscountPrice)
    {
        this.productActualPrice = productActualPrice;
        this.productDiscountPrice = productDiscountPrice;
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
