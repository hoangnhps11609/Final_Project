package edu.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.dao.SizeDAO;
import edu.poly.entity.Size;
import edu.poly.entity.SizeInventory;
import edu.poly.entity.SizeTop;
import edu.poly.service.SizeService;
@Service
public class SizeServiceImpl implements SizeService{
	@Autowired
	SizeDAO sizeDAO;

	public SizeServiceImpl(SizeDAO sizeDAO) {
		this.sizeDAO = sizeDAO;
	}

	@Override
	public <S extends Size> S save(S entity) {
		return sizeDAO.save(entity);
	}

	@Override
	public <S extends Size> Optional<S> findOne(Example<S> example) {
		return sizeDAO.findOne(example);
	}

	@Override
	public Page<Size> findAll(Pageable pageable) {
		return sizeDAO.findAll(pageable);
	}

	@Override
	public List<Size> findAll() {
		return sizeDAO.findAll();
	}

	@Override
	public List<Size> findAll(Sort sort) {
		return sizeDAO.findAll(sort);
	}

	@Override
	public List<Size> findAllById(Iterable<Integer> ids) {
		return sizeDAO.findAllById(ids);
	}

	@Override
	public Optional<Size> findById(Integer id) {
		return sizeDAO.findById(id);
	}

	@Override
	public <S extends Size> List<S> saveAll(Iterable<S> entities) {
		return sizeDAO.saveAll(entities);
	}

	@Override
	public void flush() {
		sizeDAO.flush();
	}

	@Override
	public <S extends Size> S saveAndFlush(S entity) {
		return sizeDAO.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return sizeDAO.existsById(id);
	}

	@Override
	public <S extends Size> List<S> saveAllAndFlush(Iterable<S> entities) {
		return sizeDAO.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Size> Page<S> findAll(Example<S> example, Pageable pageable) {
		return sizeDAO.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Size> entities) {
		sizeDAO.deleteInBatch(entities);
	}

	@Override
	public <S extends Size> long count(Example<S> example) {
		return sizeDAO.count(example);
	}

	@Override
	public <S extends Size> boolean exists(Example<S> example) {
		return sizeDAO.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Size> entities) {
		sizeDAO.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return sizeDAO.count();
	}

	@Override
	public void deleteById(Integer id) {
		sizeDAO.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		sizeDAO.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Size entity) {
		sizeDAO.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		sizeDAO.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		sizeDAO.deleteAllInBatch();
	}

	@Override
	public Size getOne(Integer id) {
		return sizeDAO.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Size> entities) {
		sizeDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		sizeDAO.deleteAll();
	}

	@Override
	public Size getById(Integer id) {
		return sizeDAO.getById(id);
	}

	@Override
	public <S extends Size> List<S> findAll(Example<S> example) {
		return sizeDAO.findAll(example);
	}

	@Override
	public <S extends Size> List<S> findAll(Example<S> example, Sort sort) {
		return sizeDAO.findAll(example, sort);
	}

	@Override
	public List<Size> findSizeByCate(String string) {
		return sizeDAO.findSizeByCate(string);
	}

	@Override
	public Size findBySizeId(Integer sizepro) {
		return sizeDAO.findBySizeId(sizepro);
	}

	@Override
	public List<Size> TimTheoNameCuaSize(String size) {
		// TODO Auto-generated method stub
		return sizeDAO.TimKiemBoiNameCuaSize(size);
	}

	@Override
	public List<Size> TimKiemTatCaSanPhamThuocSize(String size) {
		// TODO Auto-generated method stub
		return sizeDAO.TimKiemTatCaSanPhanThuocSize(size);
	}

	@Override
	public List<SizeTop> findSizeTop() {
		// TODO Auto-generated method stub
		return sizeDAO.findSizeTop();
	}

	@Override
	public List<SizeInventory> findSizeInventory() {
		// TODO Auto-generated method stub
		return sizeDAO.findSizeInventory();
	}
	
	
}
