package edu.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.dao.SizeDetailDAO;
import edu.poly.entity.SizeDetail;
import edu.poly.service.SizeDetailService;
@Service
public class SizeDetailServiceImpl implements SizeDetailService{
	@Autowired
	SizeDetailDAO sizeDetailDAO;

	public SizeDetailServiceImpl(SizeDetailDAO sizeDetailDAO) {
		this.sizeDetailDAO = sizeDetailDAO;
	}

	@Override
	public <S extends SizeDetail> S save(S entity) {
		return sizeDetailDAO.save(entity);
	}

	@Override
	public <S extends SizeDetail> Optional<S> findOne(Example<S> example) {
		return sizeDetailDAO.findOne(example);
	}

	@Override
	public Page<SizeDetail> findAll(Pageable pageable) {
		return sizeDetailDAO.findAll(pageable);
	}

	@Override
	public List<SizeDetail> findAll() {
		return sizeDetailDAO.findAll();
	}

	@Override
	public List<SizeDetail> findAll(Sort sort) {
		return sizeDetailDAO.findAll(sort);
	}

	@Override
	public List<SizeDetail> findAllById(Iterable<Integer> ids) {
		return sizeDetailDAO.findAllById(ids);
	}

	@Override
	public Optional<SizeDetail> findById(Integer id) {
		return sizeDetailDAO.findById(id);
	}

	@Override
	public <S extends SizeDetail> List<S> saveAll(Iterable<S> entities) {
		return sizeDetailDAO.saveAll(entities);
	}

	@Override
	public void flush() {
		sizeDetailDAO.flush();
	}

	@Override
	public <S extends SizeDetail> S saveAndFlush(S entity) {
		return sizeDetailDAO.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return sizeDetailDAO.existsById(id);
	}

	@Override
	public <S extends SizeDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
		return sizeDetailDAO.saveAllAndFlush(entities);
	}

	@Override
	public <S extends SizeDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
		return sizeDetailDAO.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<SizeDetail> entities) {
		sizeDetailDAO.deleteInBatch(entities);
	}

	@Override
	public <S extends SizeDetail> long count(Example<S> example) {
		return sizeDetailDAO.count(example);
	}

	@Override
	public <S extends SizeDetail> boolean exists(Example<S> example) {
		return sizeDetailDAO.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<SizeDetail> entities) {
		sizeDetailDAO.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return sizeDetailDAO.count();
	}

	@Override
	public void deleteById(Integer id) {
		sizeDetailDAO.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		sizeDetailDAO.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(SizeDetail entity) {
		sizeDetailDAO.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		sizeDetailDAO.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		sizeDetailDAO.deleteAllInBatch();
	}

	@Override
	public SizeDetail getOne(Integer id) {
		return sizeDetailDAO.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends SizeDetail> entities) {
		sizeDetailDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		sizeDetailDAO.deleteAll();
	}

	@Override
	public SizeDetail getById(Integer id) {
		return sizeDetailDAO.getById(id);
	}

	@Override
	public <S extends SizeDetail> List<S> findAll(Example<S> example) {
		return sizeDetailDAO.findAll(example);
	}

	@Override
	public <S extends SizeDetail> List<S> findAll(Example<S> example, Sort sort) {
		return sizeDetailDAO.findAll(example, sort);
	}
	
	
}
