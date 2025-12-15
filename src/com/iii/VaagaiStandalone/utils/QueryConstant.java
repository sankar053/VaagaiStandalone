package com.iii.VaagaiStandalone.utils;

public interface QueryConstant {

    final static String CATEGORY_SELECT = "SELECT * FROM category";
	final static String CATEGORY_INSERT = "INSERT INTO Category(categoryID, categoryName) VALUES (?, ?)";
    final static String CATEGORY_UPDATE = "UPDATE Category SET categoryName = ? WHERE categoryID = ?";
    final static String CATEGORY_DELETE = "DELETE FROM Category WHERE categoryID = ?";

}
