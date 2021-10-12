package edu.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.poly.dao.CategoryDAO;
import edu.poly.service.CategoryService;
import edu.poly.entity.Category;
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

}
