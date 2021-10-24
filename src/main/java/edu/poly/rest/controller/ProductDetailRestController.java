package edu.poly.rest.controller;

import java.util.Date;
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
	
	@PostMapping("gett")
	public ProductDetail create2(@RequestBody ProductDetail product) {
		product.setCreateDate(new Date());
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
	
	@GetMapping("get/{valued}")
	public List<ProductDetail> findbyProductName(@PathVariable("valued") String valued){
		return productDetailService.findProductByName("%"+valued+"%");
	}
	
	@GetMapping("getdetail/{valued}")
	public List<ProductDetail> findbyProductDetail(@PathVariable("valued") Integer valued){
		return productDetailService.findAllbyId(valued);
	}
	

	@GetMapping("color/{id}")
	public List<ProductDetail> TimTatCaSanPhamKhiColorBang(@PathVariable("id") Integer id){
		return productDetailService.TimTatCaSanPhamKhiColorBang(id);
	}
	
	@GetMapping("color2/{id}")
	public List<ProductDetail> TimTatCaSanPhamKhiColorBang2(@PathVariable("id") Integer id){
		return productDetailService.TimTatCaSanPhamKhiColorBang(id);
	}
	
	@GetMapping("color/count/{id}")
	public Long getCountProInColor(@PathVariable("id") Integer id){
		return productDetailService.getCountProInColor(id);
	}
	
	@GetMapping("size/count/{id}")
	public Long getCountProInSize(@PathVariable("id") Integer id){
		return productDetailService.getCountProInSize(id);
	}

	@GetMapping("size/{id}")
	public List<ProductDetail> TimTatCaSanPhamKhiSizeBang(@PathVariable("id") Integer id){
		return productDetailService.TimTatCaSanPhamKhiSizeBang(id);
	}
	
	


	
}
