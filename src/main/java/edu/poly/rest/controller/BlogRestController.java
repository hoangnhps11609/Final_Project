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

import edu.poly.entity.Blog;
import edu.poly.entity.BlogCategory;
import edu.poly.entity.Category;
import edu.poly.entity.Product;
import edu.poly.service.BlogCategoryService;
import edu.poly.service.BlogService;
import edu.poly.service.CategoryService;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/blog")
public class BlogRestController {
	@Autowired
	BlogService bService;
	
	@GetMapping()
	public List<Blog> getAll() {
		return bService.findAll();
	}
	
	@GetMapping("{id}")
	public Blog getOne(@PathVariable("id") Integer id) {
		return bService.findById(id);
	}
	
	@PostMapping
	public Blog create(@RequestBody Blog blog) {
		return bService.create(blog);
	}
	
	@PutMapping("{id}")
	public Blog update(@PathVariable("id") Integer id, @RequestBody Blog blog) {
		return bService.update(blog);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		bService.deleteById(id);
	}
	
	

}
