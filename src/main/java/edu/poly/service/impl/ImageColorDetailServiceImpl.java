package edu.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.dao.ImageColorDetailDAO;
import edu.poly.entity.ImageColorDetail;
import edu.poly.service.ImageColorDetailService;
@Service
public class ImageColorDetailServiceImpl implements ImageColorDetailService{
	@Autowired
	ImageColorDetailDAO imageColorDetailDAO;

	public ImageColorDetailServiceImpl(ImageColorDetailDAO imageColorDetailDAO) {
		this.imageColorDetailDAO = imageColorDetailDAO;
	}

	@Override
	public <S extends ImageColorDetail> S save(S entity) {
		return imageColorDetailDAO.save(entity);
	}

	@Override
	public <S extends ImageColorDetail> Optional<S> findOne(Example<S> example) {
		return imageColorDetailDAO.findOne(example);
	}

	@Override
	public Page<ImageColorDetail> findAll(Pageable pageable) {
		return imageColorDetailDAO.findAll(pageable);
	}

	@Override
	public List<ImageColorDetail> findAll() {
		return imageColorDetailDAO.findAll();
	}

	@Override
	public List<ImageColorDetail> findAll(Sort sort) {
		return imageColorDetailDAO.findAll(sort);
	}

	@Override
	public List<ImageColorDetail> findAllById(Iterable<Integer> ids) {
		return imageColorDetailDAO.findAllById(ids);
	}

	@Override
	public Optional<ImageColorDetail> findById(Integer id) {
		return imageColorDetailDAO.findById(id);
	}

	@Override
	public <S extends ImageColorDetail> List<S> saveAll(Iterable<S> entities) {
		return imageColorDetailDAO.saveAll(entities);
	}

	@Override
	public void flush() {
		imageColorDetailDAO.flush();
	}

	@Override
	public <S extends ImageColorDetail> S saveAndFlush(S entity) {
		return imageColorDetailDAO.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return imageColorDetailDAO.existsById(id);
	}

	@Override
	public <S extends ImageColorDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
		return imageColorDetailDAO.saveAllAndFlush(entities);
	}

	@Override
	public <S extends ImageColorDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
		return imageColorDetailDAO.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<ImageColorDetail> entities) {
		imageColorDetailDAO.deleteInBatch(entities);
	}

	@Override
	public <S extends ImageColorDetail> long count(Example<S> example) {
		return imageColorDetailDAO.count(example);
	}

	@Override
	public <S extends ImageColorDetail> boolean exists(Example<S> example) {
		return imageColorDetailDAO.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<ImageColorDetail> entities) {
		imageColorDetailDAO.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return imageColorDetailDAO.count();
	}

	@Override
	public void deleteById(Integer id) {
		imageColorDetailDAO.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		imageColorDetailDAO.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(ImageColorDetail entity) {
		imageColorDetailDAO.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		imageColorDetailDAO.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		imageColorDetailDAO.deleteAllInBatch();
	}

	@Override
	public ImageColorDetail getOne(Integer id) {
		return imageColorDetailDAO.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends ImageColorDetail> entities) {
		imageColorDetailDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		imageColorDetailDAO.deleteAll();
	}

	@Override
	public ImageColorDetail getById(Integer id) {
		return imageColorDetailDAO.getById(id);
	}

	@Override
	public <S extends ImageColorDetail> List<S> findAll(Example<S> example) {
		return imageColorDetailDAO.findAll(example);
	}

	@Override
	public <S extends ImageColorDetail> List<S> findAll(Example<S> example, Sort sort) {
		return imageColorDetailDAO.findAll(example, sort);
	}
	
	
}
