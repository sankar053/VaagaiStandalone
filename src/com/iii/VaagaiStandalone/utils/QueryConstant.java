package com.iii.VaagaiStandalone.utils;

public interface QueryConstant {

    final static String CATEGORY_SELECT = "SELECT * FROM category";
	final static String CATEGORY_INSERT = "INSERT INTO category(categoryID, categoryName) VALUES (?, ?)";
    final static String CATEGORY_UPDATE = "UPDATE category SET categoryName = ? WHERE categoryID = ?";
    final static String CATEGORY_DELETE = "DELETE FROM category WHERE categoryID = ?";

    final static String PRODUCTDETAILS_SELECT = "SELECT * FROM productdetails WHERE categoryID = ? ";
    final static String PRODUCTPRICE_SELECT = "SELECT id, productActualPrice,productDiscountPrice FROM productprice WHERE productID = ?";
    final static String PRODUCTRATING_SELECT = "SELECT id, productRating FROM productrating WHERE productID = ?";

    final static String PRODUCTDETAILS_INSERT = "INSERT INTO productdetails(productID, productName, productPackSize, productImage, productAttribute, categoryID) VALUES (?, ?, ?, ?, ?, ?)";
    final static String PRODUCTPRICE_INSERT = "INSERT INTO productprice(id, productActualPrice, productDiscountPrice, productID) VALUES (?, ?, ?, ?)";
    final static String PRODUCTRATING_INSERT = "INSERT INTO productrating(id, productRating, productID) VALUES (?, ?, ?)";

    final static String PRODUCTDETAILS_UPDATE = "UPDATE productdetails SET productID = ?, productName = ?, productPackSize = ?,  productImage = ?, productAttribute = ? WHERE categoryID = ?";
    final static String PRODUCTPRICE_UPDATE = "UPDATE productprice SET id = ?, productActualPrice = ?, productDiscountPrice = ? WHERE productID = ?";
    final static String PRODUCTRATING_UPDATE = "UPDATE productrating SET id = ?, productRating = ? WHERE productID = ?";

    final static String PRODUCTDETAILS_DELETE = "DELETE FROM productdetails WHERE productID = ?";
    final static String PRODUCTPRICE_DELETE = "DELETE FROM productprice WHERE productID = ?";
    final static String PRODUCTRATING_DELETE = "DELETE FROM productrating WHERE productID = ?";
}
