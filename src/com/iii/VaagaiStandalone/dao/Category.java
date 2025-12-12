package com.iii.VaagaiStandalone.dao;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Category
{
    private int categoryID;
    private String categoryName;
    private List<ProductDetails> productDetails;

 
}
