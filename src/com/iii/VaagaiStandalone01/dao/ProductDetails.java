package com.iii.VaagaiStandalone01.dao;

public class ProductDetails
{
    private int productID;
    private String productName;
    private String productImage;
    private String productPackSize;
    private String productAttribute;
    private ProductPrice productActualPrice;
    private ProductPrice productDiscountPrice;
    private ProductRating productRating;

    public ProductDetails(int productID, String productName, String productImage, String productPackSize, String productAttribute, ProductPrice productActualPrice, ProductPrice productDiscountPrice, ProductRating productRating)
    {
        this.productID = productID;
        this.productName = productName;
        this.productImage = productImage;
        this.productPackSize = productPackSize;
        this.productAttribute = productAttribute;
        this.productActualPrice = productActualPrice;
        this.productDiscountPrice = productDiscountPrice;
        this.productRating = productRating;
    }

    public String myproduct()
    {
        return "  -> Product [ID: " + productID + ", Name: " + productName + ", Image: " + productImage + ", Attribute: " + productAttribute + "]";
    }
    public String productdetails()
    {
        return "  -> Product [ID: " + productID + ", Name: " + productName + ", Image: " + productImage +", PackSize: " + productPackSize +", Attribute: " + productAttribute +
                (productActualPrice != null ? ", ActualPrice: " + productActualPrice.prodActualprice() : "") +  //Ternary operator(condition?true:false)
                (productDiscountPrice != null ? ", DiscountPrice: " + productDiscountPrice.prodDiscountprice() : "") +
                (productRating != null ? ", Rating: " + productRating.prodrating() : "") + "]";
    }

}
