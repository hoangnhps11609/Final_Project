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
import edu.poly.entity.BrandInventory;
import edu.poly.entity.BrandTop;
import edu.poly.entity.Category;
import edu.poly.entity.CategoryInventory;
import edu.poly.entity.CategoryTop;
import edu.poly.service.BrandService;
import edu.poly.service.CategoryService;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/brands")
public class BrandRestController {
	@Autowired
	BrandService bService;
	
	@GetMapping()
	public List<Brand> getAll() {
		return bService.findAll();
	}
	
	//@GetMapping("{id}")
	//public Brand getOne(@PathVariable("id") Integer id) {
		//return bService.findById(id);
	//}
	
	@PostMapping
	public Brand create(@RequestBody Brand brand) {
		return bService.create(brand);
	}
	
	@GetMapping("/top")
	public List<BrandTop> getBrandTop() {
		List<BrandTop> list = bService.findBrandTop();
		return list;
	}
	
	@PutMapping("{id}")
	public Brand update(@PathVariable("id") Integer id, @RequestBody Brand brand) {
		return bService.update(brand);
	}
	
	@GetMapping("/inventory")
	public List<BrandInventory> getBrandInventory() {
		List<BrandInventory> list = bService.findBrandInventory();
		return list;
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		bService.delete(id);
	}
	
	@GetMapping("{valued}")
	public List<Brand> getAllList(@PathVariable("valued") String valued) {
		return bService.getListBrand("%"+valued+"%");
	}
}
