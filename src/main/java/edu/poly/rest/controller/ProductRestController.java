package edu.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import edu.poly.entity.BrandInventory;
import edu.poly.entity.BrandTop;
import edu.poly.entity.Product;
import edu.poly.entity.ProductByColor;
import edu.poly.entity.ProductDetail;
import edu.poly.entity.ProductInventory;
import edu.poly.entity.ProductTop;
import edu.poly.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
	@Autowired
	ProductService pService;
	
	@GetMapping()
	public List<Product> getAll() {
		return pService.findAll(Sort.by("id").descending());
	}
	
	@GetMapping("product/{id}")
	public Product getOne(@PathVariable("id") Integer id) {
		return pService.findById(id);
	}
	
	@GetMapping("category/{cateid}")
	public List<Product> findbyCategory(@PathVariable("cateid") String cateid) {
		return pService.findByCategoryId(cateid, Sort.by("id").descending());
	}
	
	@GetMapping("/top")
	public List<ProductTop> getBrandTop() {
		List<ProductTop> list = pService.findProductTop();
		return list;
	}
	

	

	
	@GetMapping("category/count/{cateid}")
	public Long getCountInCategory(@PathVariable("cateid") String cateid) {
		return pService.getCount(cateid);
	}
	
	@GetMapping("brand/count/{brandid}")
	public Long getCountInCategory(@PathVariable("brandid") Integer brandid) {
		return pService.getCountBrand(brandid);
	}
	
	@GetMapping("productdetail/count/{id}")
	public Long getCountProDetail(@PathVariable("id") Integer id) {
		return pService.getCountProDetail(id);
	}
		
	@GetMapping("brand/{brandid}")
	public List<Product> findbyBrand(@PathVariable("brandid") Integer brandid) {
		return pService.findByBrandId(brandid);
	}
	
	@GetMapping("productCate/{cateid}")
	public List<Product> findProductByCate(@PathVariable("cateid") String cateid) {
		return pService.findProductByCategory(cateid);
	}
	
	
	
	
	@PostMapping
	public Product create(@RequestBody Product product) {
		product.setAvailable(false);

		return pService.create(product);
	}
	
	@GetMapping("/inventory")
	public List<ProductInventory> getBrandInventory() {
		List<ProductInventory> list = pService.findProductInventory();
		return list;
	}
	
	@PutMapping("{id}")
	public Product update(@PathVariable("id") Integer id, @RequestBody Product product) {
		return pService.update(product);
	}

	
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		pService.delete(id);
	}
	
	@GetMapping("getid/{valued}")
	public List<Product> getListAccountByValued2(@PathVariable("valued") Integer valued){
		return pService.ThinhWaMetMoi(valued);
		
	}
	
	@GetMapping("{valued}")
	public List<Product> getListAccountByValued(@PathVariable("valued") String valued){
		return pService.findbyName("%"+valued+"%");
	}
	
	@PutMapping("get/{id}")
	public Product update2(@PathVariable("id") Integer id, @RequestBody Product product) {
		Optional<Product> a = pService.getChio(id);
		product.setId(id);
		product.setImage(a.get().getImage());
		product.setBrand(a.get().getBrand());
		product.setCategory(a.get().getCategory());
		product.setDescription(a.get().getDescription());
		product.setDiscount(a.get().getDiscount());
		product.setGender(a.get().getGender());
		product.setName(a.get().getName());
		product.setPrice(a.get().getPrice());
		product.setAvailable(true);
		return pService.update(product);
	}
	
	@GetMapping("/color/{id}")
	public List<ProductByColor> getProInColor(@PathVariable("id") Integer id){
		return pService.getProInColor(id);
	}
}
