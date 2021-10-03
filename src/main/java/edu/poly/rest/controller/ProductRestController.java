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

import edu.poly.entity.Product;
import edu.poly.entity.ProductDetail;
import edu.poly.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
	@Autowired
	ProductService pService;
	
	@GetMapping()
	public List<Product> getAll() {
		return pService.findAll();
	}
	
	@PostMapping
	public Product create(@RequestBody Product product) {
		return pService.create(product);
	}
	
	@PutMapping("{id}")
	public Product update(@PathVariable("id") Integer id, @RequestBody Product product) {
		return pService.update(product);
	}
	
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		pService.delete(id);
	}
}
