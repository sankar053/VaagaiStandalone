package com.iii.VaagaiStandalone.dao;

import java.util.List;

public class Category
{
    private int categoryID;
    private String categoryName;
    private List<ProductDetails> productDetails;

    public Category(int categoryID, String categoryName, List<ProductDetails> productDetails)
    {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.productDetails = productDetails;
    }

    public String mycat()
    {
        return "Category [ID: " + categoryID + ", Name: " + categoryName  + "]";
    }
}
