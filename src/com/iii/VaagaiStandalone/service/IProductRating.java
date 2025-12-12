package com.iii.VaagaiStandalone.service;

import java.util.List;

import com.iii.VaagaiStandalone.dto.CategoryDto;
import com.iii.VaagaiStandalone.dto.ProductRatingDto;

public interface IProductRating {
	
	public boolean create(ProductRatingDto productRating);

	public int update(ProductRatingDto productRating);

	public int delete(int ratingID);

	public List<CategoryDto> getAll();

}
