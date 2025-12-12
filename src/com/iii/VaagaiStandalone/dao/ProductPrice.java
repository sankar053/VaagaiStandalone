package com.iii.VaagaiStandalone.dao;

public class ProductPrice
{
    private double productActualPrice;
    private double productDiscountPrice;

    public ProductPrice(double productActualPrice, double productDiscountPrice)
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
