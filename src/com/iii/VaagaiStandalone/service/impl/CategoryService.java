package com.iii.VaagaiStandalone.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.iii.VaagaiStandalone.config.DatabaseConfig;
import com.iii.VaagaiStandalone.dto.CategoryDto;
import com.iii.VaagaiStandalone.service.ICategory;
import com.iii.VaagaiStandalone.utils.QueryConstant;

public class CategoryService implements ICategory {

	@Override
	public boolean create(CategoryDto category) {
	
		try {

			try (Connection connection = DatabaseConfig.getDBConnection();
					PreparedStatement statement = connection.prepareStatement(QueryConstant.CATEGORY_INSERT)) {
				statement.setInt(1, category.getCategoryId());
				statement.setString(2, category.getCategoryName());
				// executeUpdate() -> DELETE/INSERT/UPDATE
				return statement.executeUpdate() > 0; // executeUpdate() returns integer(0 or 1)
			}
		} catch (Exception e) {
			System.err.println("Error adding category : " + category.getCategoryId());
			return false;
		}
	}

	@Override
	public int update(CategoryDto category) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int categoryId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CategoryDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
