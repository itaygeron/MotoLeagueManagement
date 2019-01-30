package com.motoleague.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motoleague.entity.Category;
import com.motoleague.service.CategoryService;
import com.motoleague.service.MapValidationErrorService;

@RestController
@RequestMapping("/api/category")
@CrossOrigin
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewCategory(
			@Valid @RequestBody Category category, 
			BindingResult result) {
		
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		
		if (errorMap != null)
			return errorMap;
		
		categoryService.saveOrUpdateCategory(category);
		
		return new ResponseEntity<Category>(category, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public Iterable<Category> getAllCategories() {
		
		return categoryService.findAllCategories();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
		
		Category category = categoryService.findCategoryById(id);
		
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoryById(@PathVariable Long id) {
		
		Category category = categoryService.findCategoryById(id);
		categoryService.deleteCategoryById(id);
		
		return new ResponseEntity<String>(
				"The category '" + category.getCategoryName() + "' has been deleted.",
				HttpStatus.OK);
	}
}
