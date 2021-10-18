package edu.poly.service;

import java.util.List;



import edu.poly.entity.Category;
import edu.poly.entity.Product;



public interface CategoryService {

	List<Category> findAll();

	Category create(Category category);

	Category update(Category category);

	void delete(String id);

	Category findById(String id);

	Category findbyCateId(String categoryID);
	
	List<Category> findByName(String valued);

<<<<<<< HEAD

=======
>>>>>>> 683e88af5f6b16de8a1789e2c3a65698f08a8027



}
