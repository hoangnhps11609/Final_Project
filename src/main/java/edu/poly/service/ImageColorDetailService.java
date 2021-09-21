package edu.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.entity.ImageColorDetail;
import edu.poly.entity.Product;

public interface ImageColorDetailService {


	Page<ImageColorDetail> findByColor(Integer color, Pageable pageable);

	<S extends ImageColorDetail> List<S> findAll(Example<S> example, Sort sort);

	<S extends ImageColorDetail> List<S> findAll(Example<S> example);

	ImageColorDetail getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends ImageColorDetail> entities);

	ImageColorDetail getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	void delete(ImageColorDetail entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	long count();

	void deleteAllInBatch(Iterable<ImageColorDetail> entities);

	<S extends ImageColorDetail> boolean exists(Example<S> example);

	<S extends ImageColorDetail> long count(Example<S> example);

	void deleteInBatch(Iterable<ImageColorDetail> entities);

	<S extends ImageColorDetail> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends ImageColorDetail> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Long id);

	<S extends ImageColorDetail> S saveAndFlush(S entity);

	void flush();

	<S extends ImageColorDetail> List<S> saveAll(Iterable<S> entities);

	Optional<ImageColorDetail> findById(Long id);

	List<ImageColorDetail> findAllById(Iterable<Long> ids);

	List<ImageColorDetail> findAll(Sort sort);

	List<ImageColorDetail> findAll();

	Page<ImageColorDetail> findAll(Pageable pageable);

	<S extends ImageColorDetail> Optional<S> findOne(Example<S> example);

	<S extends ImageColorDetail> S save(S entity);

}
