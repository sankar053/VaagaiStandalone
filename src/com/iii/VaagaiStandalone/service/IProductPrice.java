package com.iii.VaagaiStandalone.service;

import java.util.List;

import com.iii.VaagaiStandalone.dto.CategoryDto;
import com.iii.VaagaiStandalone.dto.ProductPriceDto;

public interface IProductPrice {
	
	public boolean create(ProductPriceDto productPrice);

	public int update(ProductPriceDto productPrice);

	public int delete(int priceID);

	public List<CategoryDto> getAll();

}
