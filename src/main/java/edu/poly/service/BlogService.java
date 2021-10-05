package edu.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.entity.Blog;
import edu.poly.entity.Product;

public interface BlogService {

	<S extends Blog> List<S> findAll(Example<S> example, Sort sort);

	<S extends Blog> List<S> findAll(Example<S> example);

	Blog getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Blog> entities);

	Blog getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Blog entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	long count();

	void deleteAllInBatch(Iterable<Blog> entities);

	<S extends Blog> boolean exists(Example<S> example);

	<S extends Blog> long count(Example<S> example);

	void deleteInBatch(Iterable<Blog> entities);

	<S extends Blog> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Blog> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Integer id);

	<S extends Blog> S saveAndFlush(S entity);

	void flush();

	<S extends Blog> List<S> saveAll(Iterable<S> entities);

	Blog findById(Integer id);

	List<Blog> findAllById(Iterable<Integer> ids);
	
	List<Blog> findByCategoryId(Integer cid);
	
	Page<Blog> findByCategoryId(Integer integer, Pageable pageable);

	List<Blog> findAll(Sort sort);

	List<Blog> findAll();

	Page<Blog> findAll(Pageable pageable);

	<S extends Blog> Optional<S> findOne(Example<S> example);

	<S extends Blog> S save(S entity);

	Page<Blog> findByKeyword(String string, Pageable pageable);

	Blog create(Blog blog);

	Blog update(Blog blog);
	
	List<Blog> findByName(String valed);
	
}
