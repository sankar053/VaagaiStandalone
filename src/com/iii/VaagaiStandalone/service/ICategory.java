/**
 * 
 */
package com.iii.VaagaiStandalone.service;

import java.util.List;

import com.iii.VaagaiStandalone.dto.CategoryDto;

/**
 * 
 */
public interface ICategory {

	public boolean create(CategoryDto category);

	public int update(CategoryDto category);

	public int delete(int categoryId);

	public List<CategoryDto> getAll();

}
