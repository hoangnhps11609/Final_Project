package edu.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.entity.BlogCategory;

public interface BlogCategoryService {

	<S extends BlogCategory> List<S> findAll(Example<S> example, Sort sort);

	<S extends BlogCategory> List<S> findAll(Example<S> example);

	BlogCategory getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends BlogCategory> entities);

	BlogCategory getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(BlogCategory entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	long count();

	void deleteAllInBatch(Iterable<BlogCategory> entities);

	<S extends BlogCategory> boolean exists(Example<S> example);

	<S extends BlogCategory> long count(Example<S> example);

	void deleteInBatch(Iterable<BlogCategory> entities);

	<S extends BlogCategory> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends BlogCategory> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Integer id);

	<S extends BlogCategory> S saveAndFlush(S entity);

	void flush();

	<S extends BlogCategory> List<S> saveAll(Iterable<S> entities);

	BlogCategory findById(Integer id);

	List<BlogCategory> findAllById(Iterable<Integer> ids);

	List<BlogCategory> findAll(Sort sort);

	List<BlogCategory> findAll();

	Page<BlogCategory> findAll(Pageable pageable);

	<S extends BlogCategory> Optional<S> findOne(Example<S> example);

	<S extends BlogCategory> S save(S entity);
}
