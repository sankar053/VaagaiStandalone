package com.iii.VaagaiStandalone.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
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

   

}
