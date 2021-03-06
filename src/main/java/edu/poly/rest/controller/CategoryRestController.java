package edu.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.entity.Account;
import edu.poly.entity.Category;
import edu.poly.entity.CategoryInventory;
import edu.poly.entity.CategoryTop;
import edu.poly.service.CategoryService;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
	@Autowired
	CategoryService cService;
	
	@GetMapping()
	public List<Category> getAll() {
		return cService.findAll(Sort.by("id").descending());
	}
	
	@GetMapping("/top")
	public List<CategoryTop> getCategoryTop() {
		List<CategoryTop> list = cService.findCategoryTop();
		return list;
	}
	
	@GetMapping("/inventory")
	public List<CategoryInventory> getCategoryInventory() {
		List<CategoryInventory> list = cService.findCategoryInventory();
		return list;
	}
	
	@GetMapping("getValidation/{validation}")
	public Category getID(@PathVariable("validation") String validation) {
		Category cate = cService.findValidation(validation);
		return cate;
	}
	
	@GetMapping("getcate/{id}")
	public Category getOne(@PathVariable("id") String id) {
		return cService.findById(id);
	}
	
	@GetMapping("{valued}")
	public List<Category> getOne1(@PathVariable("valued") String valued) {
		return cService.findByName("%"+valued+"%");
	}
	
	@PostMapping
	public Category create(@RequestBody Category category) {
		// kho wa ma 
	//	try {
			//String check = category.getId();
			//Optional<Category> c = cService.getChio(check);
			//if(c.isPresent()) {
				//return cService.create(null);
			//}else {
				return cService.create(category);
			//}
			//}catch(Exception ex) {
				//return cService.create(null);
			//}
	}
	
	@PutMapping("{id}")
	public Category update(@PathVariable("id") String id, @RequestBody Category category) {
		return cService.update(category);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") String id) {
		cService.delete(id);
	}
}
