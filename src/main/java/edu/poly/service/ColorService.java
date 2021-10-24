package edu.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.entity.Account;
import edu.poly.entity.BrandTop;
import edu.poly.entity.Color;
import edu.poly.entity.ColorTop;

public interface ColorService {

	<S extends Color> List<S> findAll(Example<S> example, Sort sort);

	<S extends Color> List<S> findAll(Example<S> example);

	Color getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Color> entities);

	Color getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Color entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	long count();

	void deleteAllInBatch(Iterable<Color> entities);

	<S extends Color> boolean exists(Example<S> example);

	<S extends Color> long count(Example<S> example);

	void deleteInBatch(Iterable<Color> entities);

	<S extends Color> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Color> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Integer id);

	<S extends Color> S saveAndFlush(S entity);

	void flush();

	<S extends Color> List<S> saveAll(Iterable<S> entities);

	Optional<Color> findById(Integer id);

	List<Color> findAllById(Iterable<Integer> ids);

	List<Color> findAll(Sort sort);

	List<Color> findAll();

	Page<Color> findAll(Pageable pageable);

	<S extends Color> Optional<S> findOne(Example<S> example);

	<S extends Color> S save(S entity);

	Color findByColorId(Integer color);
	
	
	Color update(Account account);
	
	List<ColorTop> findColorTop();


	

}
