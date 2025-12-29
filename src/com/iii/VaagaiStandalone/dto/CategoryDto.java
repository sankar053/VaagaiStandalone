package com.iii.VaagaiStandalone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryDto {
	private int categoryID;
	private String categoryName;

    // Constructor to getAll(), create(), update()
    public CategoryDto(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    // Constructor to delete()
    public CategoryDto(int categoryID) {
        this.categoryID = categoryID;
    }

    public String mycat()
    {
        return "Category [ID: " + categoryID + ", Name: " + categoryName  + "]";
    }
}
