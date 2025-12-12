package com.iii.VaagaiStandalone.service;

import java.util.List;

import com.iii.VaagaiStandalone.dto.CategoryDto;
import com.iii.VaagaiStandalone.dto.ProductDetailsDto;

public interface IProduct {
	
	
	public boolean create(ProductDetailsDto productDetails);

	public int update(ProductDetailsDto productDetails);

	public int delete(int productID);

	public List<CategoryDto> getAll();

}
