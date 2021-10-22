package edu.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.dao.CategoryDAO;
import edu.poly.service.CategoryService;
import edu.poly.entity.Category;
import edu.poly.entity.CategoryInventory;
import edu.poly.entity.CategoryTop;
@Service
public class CategoryServiceImp implements CategoryService{

	@Autowired
	CategoryDAO cdao;
	
	@Override
	public List<Category> findAll() {
		return cdao.findAll()	;
	}
	
	@Override
	public Category create(Category category) {
		return cdao.save(category);
	}
	@Override
	public Category update(Category category) {
		return cdao.save(category);
	}
	@Override
	public void delete(String id) {
		 cdao.deleteById(id);		
	}
	@Override
	public Category findById(String id) {
		return cdao.findById(id).get();
	}

	@Override
	public Category findbyCateId(String categoryID) {
		return cdao.findbyCateId(categoryID);
	}

	@Override
	public List<Category> findByName(String valued) {
		// TODO Auto-generated method stub
		return cdao.findbyName(valued);
	}

	

	@Override
	public List<Category> findAll(Sort sort) {
		return cdao.findAll(sort);
	}

	@Override
	public List<CategoryInventory> findCategoryInventory() {
		return cdao.findCategoryInventory();
	}

	@Override
	public List<CategoryTop> findCategoryTop() {
		return cdao.findCategoryTop();
	}


}
