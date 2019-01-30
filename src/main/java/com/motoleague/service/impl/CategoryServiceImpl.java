package com.motoleague.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motoleague.entity.Category;
import com.motoleague.exceptions.IdDoesNotExistException;
import com.motoleague.exceptions.UniqueFieldException;
import com.motoleague.repository.CategoryRepository;
import com.motoleague.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category saveOrUpdateCategory(Category category) {
		try {
			return categoryRepository.save(category);
		} catch (Exception e) {
			throw new UniqueFieldException("The category name '" + category.getCategoryName()
			+ "' is already taken.");
		}
	}

	@Override
	public Category findCategoryById(Long id) {

		return categoryRepository.findById(id).get();
	}

	@Override
	public Iterable<Category> findAllCategories() {
		
		return categoryRepository.findAll();
	}

	@Override
	public void deleteCategoryById(Long id) {

		Category category = categoryRepository.findCategoryById(id);
		
		if (category == null)
			throw new IdDoesNotExistException("Category with id " + id + " does not exist.");
		
		categoryRepository.deleteById(id);
	}

}
