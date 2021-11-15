package edu.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.dao.ProductDAO;
import edu.poly.service.ProductService;
import edu.poly.entity.OrderDetail;
import edu.poly.entity.Product;
import edu.poly.entity.ProductByColor;
import edu.poly.entity.ProductDetail;
import edu.poly.entity.ProductInventory;
import edu.poly.entity.ProductTop;
import edu.poly.entity.TopSaleAllType;

@Service
public class ProductServiceImp implements ProductService{
	@Autowired
	ProductDAO productDAO;

	public ProductServiceImp(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}


	@Override
	public List<Product> findAll(Sort sort) {
		return productDAO.findAll(sort);
	}


	@Override
	public List<Product> findAll() {

		return productDAO.findAll();
	}


	@Override
	public Product findById(Integer id) {
		return productDAO.findById(id).get();
	}


	@Override
	public List<Product> findByCategoryId(String cid, Sort sort) {
		return productDAO.findByCategoryId(cid, sort);
	}


	@Override
	public Product create(Product product) {
		return productDAO.save(product);
	}


	@Override
	public Product update(Product product) {
		return productDAO.save(product);
	}


	@Override
	public void delete(Integer id) {
		 productDAO.deleteById(id);
	}


	@Override
	public Page<Product> findByNameContaining(String name, Pageable pageable) {
		return  productDAO.findByNameContaining(name, pageable);
	}


	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productDAO.findAll(pageable);
	}


	@Override
	public Page<Product> findByCategoryId(String string, Pageable pageable) {
		return  productDAO.findByCategoryId(string, pageable);
	}


	@Override
	public Page<Product> findByPriceContaining(Double min, Double max, Pageable pageable) {
		return productDAO.findByPriceContaining(min, max, pageable);
	}


	@Override
	public Page<Product> findByCategoryIdAndPrice(String categoryID, Double min, Double max, Pageable pageable) {
		return productDAO.findByCategoryIdAndPrice(categoryID, min, max, pageable);
	}


	@Override
	public Page<Product> findByKeyword(String search, Pageable pageable) {
		return productDAO.findByKeyword(search, pageable);
	}


	@Override
	public List<Product> findByCategoryIdandGender(String categoryid, int gender) {
		return productDAO.findByCategoryIdandGender(categoryid, gender);
	}

	@Override
	public Page<Product> findByBrandId(Integer brand, Pageable pageable) {
		return productDAO.findByBrandId(brand, pageable);
	}


	@Override
	public Page<Product> findByGenderId(Integer gender, Pageable pageable) {
		return productDAO.findByGenderId(gender, pageable);
	}


	@Override
	public List<Product> findbyName(String valued) {
		
		return productDAO.findbyName(valued);
	}



	@Override
	public Page<Product> findAllTrue(Pageable pageable) {
		return productDAO.findAllTrue(pageable);
	}


	@Override
	public Page<Product> filterProduct(String cateid, String brandname, String gendername, double min, double max,
			Pageable pageable) {
		return productDAO.filterProduct(cateid, brandname, gendername, min, max, pageable);
	}


	@Override
	public Optional<Product> getChio(Integer id) {
		// TODO Auto-generated method stub
		return productDAO.getChio(id);
	}


	@Override
	public List<Product> ThinhWaMetMoi(Integer id) {
		// TODO Auto-generated method stub
		return  productDAO.ThinhWaMetMoi(id);
	}


	@Override
	public List<Product> findByBrandId(Integer brandid) {
		// TODO Auto-generated method stub
		return productDAO.findByBrandId(brandid);
	}


	@Override
	public Long getCount(String cateid) {
		return productDAO.getCount(cateid);
	}


	@Override
	public Long getCountProDetail(Integer id) {
		return productDAO.getCountProDetail(id);
	}


	@Override
	public List<ProductByColor> getProInColor(Integer id) {
		return productDAO.getProInColor(id);
	}


	@Override
	public List<Product> findByBrandId(Integer cid, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Long getCountBrand(Integer brandid) {
		// TODO Auto-generated method stub
		return productDAO.getCountBrand(brandid);
	}


	@Override
	public List<ProductTop> findProductTop() {
		// TODO Auto-generated method stub
		return productDAO.findProductTop();
	}


	@Override
	public List<ProductInventory> findProductInventory() {
		// TODO Auto-generated method stub
		return productDAO.findProductInventory();
	}


	@Override
	public List<Product> findProductByCategory(String cateid) {
		// TODO Auto-generated method stub
		return productDAO.findProductByCategory(cateid);
	}


	@Override
	public List<TopSaleAllType> findProByTopCategorySale(String cateid) {
		return productDAO.findProByTopCategorySale(cateid);
	}


	@Override
	public List<TopSaleAllType> findProByTopBrandSale(Integer cateid) {
		// TODO Auto-generated method stub
		return productDAO.findProByTopBrandSale(cateid);
	}


	@Override
	public List<Integer> findAvaiableFalse() {
		return productDAO.findAvaiableFalse();
	}
	
	@Override
	public List<Integer> findAvaiableTrue() {
		return productDAO.findAvaiableTrue();
	}


	@Override
	public List<Product> findNullPD() {
		return productDAO.findNullPD();
	}




}
