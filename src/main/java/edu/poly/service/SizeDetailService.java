package edu.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.entity.SizeDetail;

public interface SizeDetailService {

	<S extends SizeDetail> List<S> findAll(Example<S> example, Sort sort);

	<S extends SizeDetail> List<S> findAll(Example<S> example);

	SizeDetail getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends SizeDetail> entities);

	SizeDetail getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(SizeDetail entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	long count();

	void deleteAllInBatch(Iterable<SizeDetail> entities);

	<S extends SizeDetail> boolean exists(Example<S> example);

	<S extends SizeDetail> long count(Example<S> example);

	void deleteInBatch(Iterable<SizeDetail> entities);

	<S extends SizeDetail> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends SizeDetail> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Integer id);

	<S extends SizeDetail> S saveAndFlush(S entity);

	void flush();

	<S extends SizeDetail> List<S> saveAll(Iterable<S> entities);

	Optional<SizeDetail> findById(Integer id);

	List<SizeDetail> findAllById(Iterable<Integer> ids);

	List<SizeDetail> findAll(Sort sort);

	List<SizeDetail> findAll();

	Page<SizeDetail> findAll(Pageable pageable);

	<S extends SizeDetail> Optional<S> findOne(Example<S> example);

	<S extends SizeDetail> S save(S entity);

}
