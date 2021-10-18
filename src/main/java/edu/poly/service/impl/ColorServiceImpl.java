package edu.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.dao.ColorDAO;
import edu.poly.entity.Account;
import edu.poly.entity.Color;
import edu.poly.service.ColorService;
@Service
public class ColorServiceImpl implements ColorService{
	@Autowired
	ColorDAO colorDAO;

	public ColorServiceImpl(ColorDAO colorDAO) {
		this.colorDAO = colorDAO;
	}

	@Override
	public <S extends Color> S save(S entity) {
		return colorDAO.save(entity);
	}

	@Override
	public <S extends Color> Optional<S> findOne(Example<S> example) {
		return colorDAO.findOne(example);
	}

	@Override
	public Page<Color> findAll(Pageable pageable) {
		return colorDAO.findAll(pageable);
	}

	@Override
	public List<Color> findAll() {
		return colorDAO.findAll();
	}

	@Override
	public List<Color> findAll(Sort sort) {
		return colorDAO.findAll(sort);
	}

	@Override
	public List<Color> findAllById(Iterable<Integer> ids) {
		return colorDAO.findAllById(ids);
	}

	@Override
	public Optional<Color> findById(Integer id) {
		return colorDAO.findById(id);
	}

	@Override
	public <S extends Color> List<S> saveAll(Iterable<S> entities) {
		return colorDAO.saveAll(entities);
	}

	@Override
	public void flush() {
		colorDAO.flush();
	}

	@Override
	public <S extends Color> S saveAndFlush(S entity) {
		return colorDAO.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return colorDAO.existsById(id);
	}

	@Override
	public <S extends Color> List<S> saveAllAndFlush(Iterable<S> entities) {
		return colorDAO.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Color> Page<S> findAll(Example<S> example, Pageable pageable) {
		return colorDAO.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Color> entities) {
		colorDAO.deleteInBatch(entities);
	}

	@Override
	public <S extends Color> long count(Example<S> example) {
		return colorDAO.count(example);
	}

	@Override
	public <S extends Color> boolean exists(Example<S> example) {
		return colorDAO.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Color> entities) {
		colorDAO.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return colorDAO.count();
	}

	@Override
	public void deleteById(Integer id) {
		colorDAO.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		colorDAO.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Color entity) {
		colorDAO.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		colorDAO.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		colorDAO.deleteAllInBatch();
	}

	@Override
	public Color getOne(Integer id) {
		return colorDAO.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Color> entities) {
		colorDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		colorDAO.deleteAll();
	}

	@Override
	public Color getById(Integer id) {
		return colorDAO.getById(id);
	}

	@Override
	public <S extends Color> List<S> findAll(Example<S> example) {
		return colorDAO.findAll(example);
	}

	@Override
	public <S extends Color> List<S> findAll(Example<S> example, Sort sort) {
		return colorDAO.findAll(example, sort);
	}

	@Override
	public Color findByColorId(Integer color) {
		return colorDAO.findByColorId(color);
	}

	@Override
	public Color update(Account account) {
		// TODO Auto-generated method stub
		return null;
	};
	
	
}
