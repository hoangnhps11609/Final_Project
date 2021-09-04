package edu.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.poly.dao.ProductDAO;
import edu.poly.service.ProductService;
import edu.poly.entity.Product;

@Service
public class ProductServiceImp implements ProductService{
	@Autowired
	ProductDAO productDAO;

	@Override
	public List<Product> findAll() {

		return productDAO.findAll();
	}


	@Override
	public Product findById(Integer id) {
		
		return productDAO.findById(id).get();
	}


	@Override
	public List<Product> findByCategoryId(String cid) {
		
		return productDAO.findByCategoryId(cid);
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
		 productDAO.deleteById(id);;
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


}
