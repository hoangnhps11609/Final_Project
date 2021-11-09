package edu.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import edu.poly.entity.Category;
import edu.poly.entity.CategoryInventory;
import edu.poly.entity.CategoryTop;
import edu.poly.entity.Product;



public interface CategoryService {

	List<Category> findAll();
	
	List<Category> findAll(Sort sort);

	Category create(Category category);

	Category update(Category category);

	void delete(String id);

	Category findById(String id);

	Category findbyCateId(String categoryID);
	
	List<Category> findByName(String valued);

	List<CategoryInventory> findCategoryInventory();

	List<CategoryTop> findCategoryTop();
	
	Optional<Category> getChio(String id);




}