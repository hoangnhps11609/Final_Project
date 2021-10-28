package edu.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.entity.OrderDetail;
import edu.poly.entity.Product;
import edu.poly.entity.ProductByColor;
import edu.poly.entity.ProductDetail;
import edu.poly.entity.ProductInventory;
import edu.poly.entity.ProductTop;
import edu.poly.entity.TopSaleAllType;

public interface ProductService {

	List<Product> findAll();


	Product findById(Integer id);
//
//
	List<Product> findByCategoryId(String cid, Sort sort);
	
	List<Product> findByBrandId(Integer cid, Sort sort);


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

	Page<Product> findByBrandId(Integer brand, Pageable pageable);


	Page<Product> findByGenderId(Integer gender, Pageable pageable);
	
	List<Product> findbyName(String valued);
	

	Page<Product> findAllTrue(Pageable pageable);


	Page<Product> filterProduct(String cateid, String brandname, String gendername, double min, double max, Pageable pageable);
	
	Optional<Product> getChio(Integer id);
	
	List<Product> ThinhWaMetMoi(Integer id);


	List<Product> findByBrandId(Integer brandid);


	List<Product> findAll(Sort sort);


	Long getCount(String cateid);

	Long getCountBrand(Integer brandid);


	Long getCountProDetail(Integer id);


	List<ProductByColor> getProInColor(Integer id);	
	
	List<ProductTop> findProductTop();
	
	List<ProductInventory> findProductInventory();
	
	List<Product> findProductByCategory(String cateid);


	List<TopSaleAllType> findProByTopCategorySale(String cateid);
	
	List<TopSaleAllType> findProByTopBrandSale(Integer cateid);


//	List<Integer> findAvaiableTrue();
//
//	List<Integer> findAvaiableFalse();





}
