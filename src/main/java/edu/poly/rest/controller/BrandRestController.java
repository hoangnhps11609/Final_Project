package edu.poly.rest.controller;

import java.util.List;
import java.util.Optional;

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

import edu.poly.entity.Brand;
import edu.poly.entity.Category;
import edu.poly.service.BrandService;
import edu.poly.service.CategoryService;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/brand")
public class BrandRestController {
	@Autowired
	BrandService bService;
	
	@GetMapping()
	public List<Brand> getAll() {
		return bService.findAll();
	}
	
	@GetMapping("{id}")
	public Brand getOne(@PathVariable("id") Integer id) {
		return bService.findById(id);
	}
	
	@PostMapping
	public Brand create(@RequestBody Brand brand) {
		return bService.create(brand);
	}
	
	@PutMapping("{id}")
	public Brand update(@PathVariable("id") Integer id, @RequestBody Brand brand) {
		return bService.update(brand);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		bService.delete(id);
	}
}
