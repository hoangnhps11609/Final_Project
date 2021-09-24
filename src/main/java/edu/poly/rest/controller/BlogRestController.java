package edu.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.entity.BlogCategory;
import edu.poly.entity.Category;
import edu.poly.entity.Product;
import edu.poly.service.BlogCategoryService;
import edu.poly.service.CategoryService;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/blogcates")
public class BlogRestController {
	@Autowired
	BlogCategoryService bcService;
	
	@GetMapping()
	public List<BlogCategory> getAll() {
		return bcService.findAll();
	}
	
	@GetMapping("{id}")
	public BlogCategory getOne(@PathVariable("id") Integer id) {
		return bcService.findById(id);
	}
	
	

}
