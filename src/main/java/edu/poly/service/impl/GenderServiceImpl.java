package edu.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.dao.GenderDAO;
import edu.poly.entity.Gender;
import edu.poly.service.GenderService;
@Service
public class GenderServiceImpl implements GenderService{
	@Autowired
	GenderDAO genderDAO;

	public GenderServiceImpl(GenderDAO genderDAO) {
		this.genderDAO = genderDAO;
	}

	@Override
	public <S extends Gender> S save(S entity) {
		return genderDAO.save(entity);
	}

	@Override
	public <S extends Gender> Optional<S> findOne(Example<S> example) {
		return genderDAO.findOne(example);
	}

	@Override
	public Page<Gender> findAll(Pageable pageable) {
		return genderDAO.findAll(pageable);
	}

	@Override
	public List<Gender> findAll() {
		return genderDAO.findAll();
	}

	@Override
	public List<Gender> findAll(Sort sort) {
		return genderDAO.findAll(sort);
	}

	@Override
	public List<Gender> findAllById(Iterable<Integer> ids) {
		return genderDAO.findAllById(ids);
	}

	@Override
	public Optional<Gender> findById(Integer id) {
		return genderDAO.findById(id);
	}

	@Override
	public <S extends Gender> List<S> saveAll(Iterable<S> entities) {
		return genderDAO.saveAll(entities);
	}

	@Override
	public void flush() {
		genderDAO.flush();
	}

	@Override
	public <S extends Gender> S saveAndFlush(S entity) {
		return genderDAO.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return genderDAO.existsById(id);
	}

	@Override
	public <S extends Gender> List<S> saveAllAndFlush(Iterable<S> entities) {
		return genderDAO.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Gender> Page<S> findAll(Example<S> example, Pageable pageable) {
		return genderDAO.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Gender> entities) {
		genderDAO.deleteInBatch(entities);
	}

	@Override
	public <S extends Gender> long count(Example<S> example) {
		return genderDAO.count(example);
	}

	@Override
	public <S extends Gender> boolean exists(Example<S> example) {
		return genderDAO.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Gender> entities) {
		genderDAO.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return genderDAO.count();
	}

	@Override
	public void deleteById(Integer id) {
		genderDAO.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		genderDAO.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Gender entity) {
		genderDAO.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		genderDAO.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		genderDAO.deleteAllInBatch();
	}

	@Override
	public Gender getOne(Integer id) {
		return genderDAO.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Gender> entities) {
		genderDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		genderDAO.deleteAll();
	}

	@Override
	public Gender getById(Integer id) {
		return genderDAO.getById(id);
	}

	@Override
	public <S extends Gender> List<S> findAll(Example<S> example) {
		return genderDAO.findAll(example);
	}

	@Override
	public <S extends Gender> List<S> findAll(Example<S> example, Sort sort) {
		return genderDAO.findAll(example, sort);
	}

	@Override
	public Gender findbyGenderId(Integer gender) {
		return genderDAO.findbyGenderId(gender);
	}
	
	
}
