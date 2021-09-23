package edu.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.entity.Voucher;

public interface VoucherService {

	<S extends Voucher> List<S> findAll(Example<S> example, Sort sort);

	<S extends Voucher> List<S> findAll(Example<S> example);

	Voucher getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Voucher> entities);

	Voucher getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Voucher entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	long count();

	void deleteAllInBatch(Iterable<Voucher> entities);

	<S extends Voucher> boolean exists(Example<S> example);

	<S extends Voucher> long count(Example<S> example);

	void deleteInBatch(Iterable<Voucher> entities);

	<S extends Voucher> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Voucher> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Integer id);

	<S extends Voucher> S saveAndFlush(S entity);

	void flush();

	<S extends Voucher> List<S> saveAll(Iterable<S> entities);


	List<Voucher> findAllById(Iterable<Integer> ids);

	List<Voucher> findAll(Sort sort);

	List<Voucher> findAll();

	Page<Voucher> findAll(Pageable pageable);

	<S extends Voucher> Optional<S> findOne(Example<S> example);

	<S extends Voucher> S save(S entity);

	Voucher update(Voucher product);

}
