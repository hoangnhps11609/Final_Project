package edu.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.entity.Gender;

public interface GenderService {

	<S extends Gender> List<S> findAll(Example<S> example, Sort sort);

	<S extends Gender> List<S> findAll(Example<S> example);

	Gender getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Gender> entities);

	Gender getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Gender entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	long count();

	void deleteAllInBatch(Iterable<Gender> entities);

	<S extends Gender> boolean exists(Example<S> example);

	<S extends Gender> long count(Example<S> example);

	void deleteInBatch(Iterable<Gender> entities);

	<S extends Gender> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Gender> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Integer id);

	<S extends Gender> S saveAndFlush(S entity);

	void flush();

	<S extends Gender> List<S> saveAll(Iterable<S> entities);

	Optional<Gender> findById(Integer id);

	List<Gender> findAllById(Iterable<Integer> ids);

	List<Gender> findAll(Sort sort);

	List<Gender> findAll();

	Page<Gender> findAll(Pageable pageable);

	<S extends Gender> Optional<S> findOne(Example<S> example);

	<S extends Gender> S save(S entity);

	Gender findbyGenderId(Integer gender);

}
