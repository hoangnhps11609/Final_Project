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
import edu.poly.entity.Report;
import edu.poly.service.ProductDetailService;
import edu.poly.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/productdetails")
public class ProductDetailRestController {
	@Autowired
	ProductDetailService productDetailService;
	
	@GetMapping()
	public List<ProductDetail> getAll() {
		return productDetailService.findAll();
	}
	
	@GetMapping("{id}")
	public ProductDetail getOne(@PathVariable("id") Long id) {
		return productDetailService.findbyId(id);
	}
	
	@PostMapping
	public ProductDetail create(@RequestBody ProductDetail product) {
		return productDetailService.create(product);
	}
	
	@PutMapping("{id}")
	public ProductDetail update(@PathVariable("id") Integer id, @RequestBody ProductDetail product) {
		return productDetailService.update(product);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		productDetailService.delete(id);
	}
	
//	@GetMapping("/report1")
//	public List<Report> getReport(){
//		return productDetailService.getReport();
//	}

	
}
