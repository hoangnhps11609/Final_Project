package edu.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.dao.ProductDetailDAO;
import edu.poly.entity.Color;
import edu.poly.entity.ColorPro;
import edu.poly.entity.Product;
import edu.poly.entity.ProductDetail;
import edu.poly.entity.SizePro;
import edu.poly.service.ProductDetailService;
@Service
public class ProductDetailServiceImpl implements ProductDetailService{
	
	@Autowired
	ProductDetailDAO productDetailDAO;

	public ProductDetailServiceImpl(ProductDetailDAO productDetailDAO) {
		this.productDetailDAO = productDetailDAO;
	}

	@Override
	public <S extends ProductDetail> S save(S entity) {
		return productDetailDAO.save(entity);
	}

	@Override
	public <S extends ProductDetail> Optional<S> findOne(Example<S> example) {
		return productDetailDAO.findOne(example);
	}

	@Override
	public Page<ProductDetail> findAll(Pageable pageable) {
		return productDetailDAO.findAll(pageable);
	}

	@Override
	public List<ProductDetail> findAll() {
		return productDetailDAO.findAll();
	}

	@Override
	public List<ProductDetail> findAll(Sort sort) {
		return productDetailDAO.findAll(sort);
	}

	@Override
	public List<ProductDetail> findAllById(Iterable<Long> ids) {
		return productDetailDAO.findAllById(ids);
	}

	@Override
	public Optional<ProductDetail> findById(Long id) {
		return productDetailDAO.findById(id);
	}

	@Override
	public <S extends ProductDetail> List<S> saveAll(Iterable<S> entities) {
		return productDetailDAO.saveAll(entities);
	}

	@Override
	public void flush() {
		productDetailDAO.flush();
	}

	@Override
	public <S extends ProductDetail> S saveAndFlush(S entity) {
		return productDetailDAO.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return productDetailDAO.existsById(id);
	}

	@Override
	public <S extends ProductDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productDetailDAO.saveAllAndFlush(entities);
	}

	@Override
	public <S extends ProductDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productDetailDAO.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<ProductDetail> entities) {
		productDetailDAO.deleteInBatch(entities);
	}

	@Override
	public <S extends ProductDetail> long count(Example<S> example) {
		return productDetailDAO.count(example);
	}

	@Override
	public <S extends ProductDetail> boolean exists(Example<S> example) {
		return productDetailDAO.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<ProductDetail> entities) {
		productDetailDAO.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return productDetailDAO.count();
	}

	@Override
	public void deleteById(Long id) {
		productDetailDAO.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		productDetailDAO.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(ProductDetail entity) {
		productDetailDAO.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		productDetailDAO.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		productDetailDAO.deleteAllInBatch();
	}

	@Override
	public ProductDetail getOne(Long id) {
		return productDetailDAO.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends ProductDetail> entities) {
		productDetailDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		productDetailDAO.deleteAll();
	}

	@Override
	public ProductDetail getById(Long id) {
		return productDetailDAO.getById(id);
	}

	@Override
	public <S extends ProductDetail> List<S> findAll(Example<S> example) {
		return productDetailDAO.findAll(example);
	}

	@Override
	public <S extends ProductDetail> List<S> findAll(Example<S> example, Sort sort) {
		return productDetailDAO.findAll(example, sort);
	}

	@Override
	public Page<ProductDetail> findByColor(Integer color, Pageable pageable) {
		return productDetailDAO.findByColor(color, pageable);
	}

	@Override
	public Page<ProductDetail> findBySize(Integer sizepro, Pageable pageable) {
		return productDetailDAO.findBySize(sizepro, pageable);
	}

	@Override
	public Product findByProductId(Integer id) {
		return productDetailDAO.findByProductId(id);
	}

	@Override
	public List<ColorPro> getColorByProduct(Integer id, Integer sizepro) {
		return productDetailDAO.getColorByProduct(id, sizepro);
	}
	
	@Override
	public List<ColorPro> getColorByProduct(Integer id) {
		return productDetailDAO.getColorByProduct(id);
	}
	
	@Override
	public List<SizePro> getSizeByProduct(Integer id) {
		return productDetailDAO.getSizeByProduct(id);
	}

	@Override
	public List<SizePro> getSizeByProduct(Integer id, Integer colorpro) {
		return productDetailDAO.getSizeByProduct(id, colorpro);
	}

	@Override
	public List<ProductDetail> findByProductIDandSizeID(Integer id, Integer sizepro) {
		return productDetailDAO.findByProductIDandSizeID(id, sizepro);
	}

	@Override
	public ProductDetail findbyId(Long id) {
		return productDetailDAO.findById(id).get();
	}

	@Override
	public ProductDetail create(ProductDetail product) {
		return productDetailDAO.save(product);
	}

	@Override
	public ProductDetail update(ProductDetail product) {
		return productDetailDAO.save(product);
	}

	@Override
	public void delete(Long id) {
		productDetailDAO.deleteById(id);		
	}

	@Override
	public Page<ProductDetail> filterAllProduct(String cateid, int i, int j, int k, int l, double m, double n,
			Pageable pageable) {
		return productDetailDAO.filterAllProduct(cateid, i, j, k, l, m, n, pageable);
	}

}
