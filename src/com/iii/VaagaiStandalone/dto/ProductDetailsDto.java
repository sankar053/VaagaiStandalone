package com.iii.VaagaiStandalone.dto;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

public class ProductDetailsDto {
	private int productID;
	private String productName;
    private String productPackSize;
	private String productImage;
	private String productAttribute;
    private int categoryID;
    private int productPriceID;
    private ProductPriceDto productActualPrice;
    private ProductPriceDto productDiscountPrice;
    private int productRatingID;
    private ProductRatingDto productRating;

    // Constructor for getAll()
    public ProductDetailsDto(int productID, String productName, String productPackSize, String productImage, String productAttribute, ProductPriceDto productActualPrice, ProductPriceDto productDiscountPrice, ProductRatingDto productRating) {
        this.productID = productID;
        this.productName = productName;
        this.productPackSize = productPackSize;
        this.productImage = productImage;
        this.productAttribute = productAttribute;
        this.productActualPrice = productActualPrice;
        this.productDiscountPrice = productDiscountPrice;
        this.productRating = productRating;
    }

    // Constructor for create()
    public ProductDetailsDto(int productID, String productName, String productPackSize, String productImage, String productAttribute, int categoryID) {
        this.productID = productID;
        this.productName = productName;
        this.productPackSize = productPackSize;
        this.productImage = productImage;
        this.productAttribute = productAttribute;
        this.categoryID = categoryID;
    }

    public ProductDetailsDto(int productID) {
        this.productID = productID;
    }

    //    public String myproduct()
//    {
//        return "  -> Product [ID: " + productID + ", Name: " + productName + ", Image: " + productImage + ", Attribute: " + productAttribute + "]";
//    }
    public String productdetails()
    {
        return "  -> Product [ID: " + productID + ", Name: " + productName + ", PackSize: " + productPackSize +", Image: " + productImage + ", Attribute: " + productAttribute +
                (productActualPrice != null ? ", ActualPrice: " + productActualPrice.prodActualprice() : "") +  //Ternary operator(condition?true:false)
                (productDiscountPrice != null ? ", DiscountPrice: " + productDiscountPrice.prodDiscountprice() : "") +
                (productRating != null ? ", Rating: " + productRating.prodrating() : "") + "]";
    }
}
