package edu.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.entity.Brand;

public interface BrandService {

	<S extends Brand> List<S> findAll(Example<S> example, Sort sort);

	<S extends Brand> List<S> findAll(Example<S> example);

	Brand getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Brand> entities);

	Brand getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Brand entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	long count();

	void deleteAllInBatch(Iterable<Brand> entities);

	<S extends Brand> boolean exists(Example<S> example);

	<S extends Brand> long count(Example<S> example);

	void deleteInBatch(Iterable<Brand> entities);

	<S extends Brand> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Brand> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Integer id);

	<S extends Brand> S saveAndFlush(S entity);

	void flush();

	<S extends Brand> List<S> saveAll(Iterable<S> entities);

	Brand findById(Integer id);

	List<Brand> findAllById(Iterable<Integer> ids);

	List<Brand> findAll(Sort sort);

	List<Brand> findAll();

	Page<Brand> findAll(Pageable pageable);

	<S extends Brand> Optional<S> findOne(Example<S> example);

	<S extends Brand> S save(S entity);

	Brand create(Brand brand);

	Brand update(Brand brand);

	void delete(Integer id);
	
	List<Brand> getListBrand (String valued);

}
