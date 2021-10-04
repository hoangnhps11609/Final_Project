package edu.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.entity.Comment;

public interface CommentService {

	<S extends Comment> List<S> findAll(Example<S> example, Sort sort);

	<S extends Comment> List<S> findAll(Example<S> example);

	Comment getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Comment> entities);

	Comment getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Comment entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	long count();

	void deleteAllInBatch(Iterable<Comment> entities);

	<S extends Comment> boolean exists(Example<S> example);

	<S extends Comment> long count(Example<S> example);

	void deleteInBatch(Iterable<Comment> entities);

	<S extends Comment> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Comment> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Integer id);

	<S extends Comment> S saveAndFlush(S entity);

	void flush();

	<S extends Comment> List<S> saveAll(Iterable<S> entities);

	Optional<Comment> findById(Integer id);

	List<Comment> findAllById(Iterable<Integer> ids);

	List<Comment> findAll(Sort sort);

	List<Comment> findAll();

	Page<Comment> findAll(Pageable pageable);

	<S extends Comment> Optional<S> findOne(Example<S> example);

	<S extends Comment> S save(S entity);

	List<Comment> findByProductId(Integer id);

}
