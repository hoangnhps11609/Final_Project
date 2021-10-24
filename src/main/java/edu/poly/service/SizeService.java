package edu.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.entity.Size;
import edu.poly.entity.SizeInventory;
import edu.poly.entity.SizeTop;

public interface SizeService {

	<S extends Size> List<S> findAll(Example<S> example, Sort sort);

	<S extends Size> List<S> findAll(Example<S> example);

	Size getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Size> entities);

	Size getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Size entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	long count();

	void deleteAllInBatch(Iterable<Size> entities);

	<S extends Size> boolean exists(Example<S> example);

	<S extends Size> long count(Example<S> example);

	void deleteInBatch(Iterable<Size> entities);

	<S extends Size> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Size> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Integer id);

	<S extends Size> S saveAndFlush(S entity);

	void flush();

	<S extends Size> List<S> saveAll(Iterable<S> entities);

	Optional<Size> findById(Integer id);

	List<Size> findAllById(Iterable<Integer> ids);

	List<Size> findAll(Sort sort);

	List<Size> findAll();

	Page<Size> findAll(Pageable pageable);

	<S extends Size> Optional<S> findOne(Example<S> example);

	<S extends Size> S save(S entity);

	List<Size> findSizeByCate(String string);

	Size findBySizeId(Integer sizepro);
	
	List<Size> TimTheoNameCuaSize(String size);
	
	List<Size> TimKiemTatCaSanPhamThuocSize(String size);
	
	List<SizeTop> findSizeTop();
	
	List<SizeInventory> findSizeInventory();


}
