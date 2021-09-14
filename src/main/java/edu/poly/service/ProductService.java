package edu.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.poly.entity.Product;

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

}
