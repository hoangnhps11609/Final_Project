package edu.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.entity.BlogCategory;
import edu.poly.service.BlogCategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/blogcates")
public class BlogCategoriesRestController {
	@Autowired
	BlogCategoryService blogcateService;
	
	
	@GetMapping()
	public List<BlogCategory> getAll() {
		return blogcateService.findAll();
	}
	
	
	@GetMapping("{id}")
	public BlogCategory getOne(@PathVariable("id") Integer id) {
		return blogcateService.findById(id);
	}
}
