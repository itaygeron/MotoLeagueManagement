package com.motoleague.service;

import com.motoleague.entity.Category;

public interface CategoryService {
	
	Category saveOrUpdateCategory(Category categpry);
	
	Category findCategoryById(Long id);
	
	Iterable<Category> findAllCategories();
	
	void deleteCategoryById(Long id);

}
