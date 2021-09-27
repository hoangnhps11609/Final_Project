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
import edu.poly.service.BlogCategoryService;



	@CrossOrigin("*")
	@RestController
	@RequestMapping("/rest/blogcategories")
	public class BlogCategoryRestController {
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
		
		@PostMapping
		public BlogCategory create(@RequestBody BlogCategory blogcategory) {
			return bcService.create(blogcategory);
		}
		
		@PutMapping("{id}")
		public BlogCategory update(@PathVariable("id") Integer id, @RequestBody BlogCategory blogcategory) {
			return bcService.update(blogcategory);
		}
		
		@DeleteMapping("{id}")
		public void delete(@PathVariable("id") Integer id) {
			bcService.delete(id);
		}
	}


