package com.iii.VaagaiStandalone.service;

import java.util.List;

import com.iii.VaagaiStandalone.dto.CategoryDto;
import com.iii.VaagaiStandalone.dto.ProductDetailsDto;
import com.iii.VaagaiStandalone.dto.ProductPriceDto;
import com.iii.VaagaiStandalone.dto.ProductRatingDto;

public interface IProduct {
	
	
	public boolean create(ProductDetailsDto productDetails, ProductPriceDto productPrice, ProductRatingDto productRating);

	public boolean update(ProductDetailsDto productdetails,ProductPriceDto productprice, ProductRatingDto productRating);

	public boolean delete(ProductDetailsDto productID);

	public List<ProductDetailsDto> getAll(CategoryDto categoryId);

}
