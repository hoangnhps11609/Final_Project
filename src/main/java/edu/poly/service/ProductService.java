package edu.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.poly.entity.Product;
import edu.poly.entity.ProductDetail;
import edu.poly.entity.Report;


public interface ProductService {

	List<Product> findAll();


	Product findById(Integer id);
//
//
	List<Product> findByCategoryId(String cid);


	Product create(Product product);


	Product update(Product product);


	void delete(Integer id);


	Page<Product> findByNameContaining(String name, Pageable pageable);


	Page<Product> findAll(Pageable pageable);


	Page<Product> findByCategoryId(String string, Pageable pageable);


	Page<Product> findByPriceContaining(Double min, Double max, Pageable pageable);


	Page<Product> findByCategoryIdAndPrice(String categoryID, Double min, Double max, Pageable pageable);


	Page<Product> findByKeyword(String search, Pageable pageable);


	List<Product> findByCategoryIdandGender(String categoryid, int gender);


	List<Report> getReport();


	Page<Product> findByBrandId(Integer brand, Pageable pageable);


	Page<Product> findByGenderId(Integer gender, Pageable pageable);
	
	List<Product> findbyName(String valued);
	

	Page<Product> findAllTrue(Pageable pageable);


	Page<Product> filterProduct(String cateid, String brandname, String gendername, double min, double max, Pageable pageable);

}
