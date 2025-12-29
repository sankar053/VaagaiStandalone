package com.iii.VaagaiStandalone.service;

import java.util.List;

import com.iii.VaagaiStandalone.dto.CategoryDto;

public interface ICategory {

	public boolean create(CategoryDto category);

	public boolean update(CategoryDto category);

	public boolean delete(CategoryDto categoryId);

	public List<CategoryDto> getAll();

}
