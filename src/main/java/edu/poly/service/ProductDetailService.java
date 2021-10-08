package edu.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.entity.Color;
import edu.poly.entity.ColorPro;
import edu.poly.entity.OrderDetail;
import edu.poly.entity.Product;
import edu.poly.entity.ProductDetail;
import edu.poly.entity.SizePro;

public interface ProductDetailService {

	<S extends ProductDetail> List<S> findAll(Example<S> example, Sort sort);

	<S extends ProductDetail> List<S> findAll(Example<S> example);

	ProductDetail getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends ProductDetail> entities);

	ProductDetail getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	void delete(ProductDetail entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	long count();

	void deleteAllInBatch(Iterable<ProductDetail> entities);

	<S extends ProductDetail> boolean exists(Example<S> example);

	<S extends ProductDetail> long count(Example<S> example);

	void deleteInBatch(Iterable<ProductDetail> entities);

	<S extends ProductDetail> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends ProductDetail> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Long id);

	<S extends ProductDetail> S saveAndFlush(S entity);

	void flush();

	<S extends ProductDetail> List<S> saveAll(Iterable<S> entities);

	Optional<ProductDetail> findById(Long id);

	List<ProductDetail> findAllById(Iterable<Long> ids);

	List<ProductDetail> findAll(Sort sort);

	List<ProductDetail> findAll();

	Page<ProductDetail> findAll(Pageable pageable);

	<S extends ProductDetail> Optional<S> findOne(Example<S> example);

	<S extends ProductDetail> S save(S entity);

	Page<ProductDetail> findByColor(Integer color, Pageable pageable);

	Page<ProductDetail> findBySize(Integer sizepro, Pageable pageable);

	Product findByProductId(Integer id);

	List<ColorPro> getColorByProduct(Integer id, Integer sizepro);

	List<SizePro> getSizeByProduct(Integer id);

	List<ColorPro> getColorByProduct(Integer id);

	List<SizePro> getSizeByProduct(Integer id, Integer colorpro);

	List<ProductDetail> findByProductIDandSizeID(Integer id, Integer sizepro);
	
	ProductDetail findbyId(Long id);
	
	ProductDetail create(ProductDetail product);

	ProductDetail update(ProductDetail product);

	void delete(Long id);

	Page<ProductDetail> filterProductDetail(String cateid, String brandname, String sizeproname, String gendername, String colorname, double min, double max, Pageable pageable);
	

}
