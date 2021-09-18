package edu.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.dao.BlogCategoryDAO;
import edu.poly.entity.BlogCategory;
import edu.poly.service.BlogCategoryService;
@Service
public class BlogCategoryServiceImpl implements BlogCategoryService{
	
	@Autowired
	BlogCategoryDAO blogCategoryDAO;

	public BlogCategoryServiceImpl(BlogCategoryDAO blogCategoryDAO) {
		this.blogCategoryDAO = blogCategoryDAO;
	}

	@Override
	public <S extends BlogCategory> S save(S entity) {
		return blogCategoryDAO.save(entity);
	}

	@Override
	public <S extends BlogCategory> Optional<S> findOne(Example<S> example) {
		return blogCategoryDAO.findOne(example);
	}

	@Override
	public Page<BlogCategory> findAll(Pageable pageable) {
		return blogCategoryDAO.findAll(pageable);
	}

	@Override
	public List<BlogCategory> findAll() {
		return blogCategoryDAO.findAll();
	}

	@Override
	public List<BlogCategory> findAll(Sort sort) {
		return blogCategoryDAO.findAll(sort);
	}

	@Override
	public List<BlogCategory> findAllById(Iterable<Integer> ids) {
		return blogCategoryDAO.findAllById(ids);
	}

	@Override
	public Optional<BlogCategory> findById(Integer id) {
		return blogCategoryDAO.findById(id);
	}

	@Override
	public <S extends BlogCategory> List<S> saveAll(Iterable<S> entities) {
		return blogCategoryDAO.saveAll(entities);
	}

	@Override
	public void flush() {
		blogCategoryDAO.flush();
	}

	@Override
	public <S extends BlogCategory> S saveAndFlush(S entity) {
		return blogCategoryDAO.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return blogCategoryDAO.existsById(id);
	}

	@Override
	public <S extends BlogCategory> List<S> saveAllAndFlush(Iterable<S> entities) {
		return blogCategoryDAO.saveAllAndFlush(entities);
	}

	@Override
	public <S extends BlogCategory> Page<S> findAll(Example<S> example, Pageable pageable) {
		return blogCategoryDAO.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<BlogCategory> entities) {
		blogCategoryDAO.deleteInBatch(entities);
	}

	@Override
	public <S extends BlogCategory> long count(Example<S> example) {
		return blogCategoryDAO.count(example);
	}

	@Override
	public <S extends BlogCategory> boolean exists(Example<S> example) {
		return blogCategoryDAO.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<BlogCategory> entities) {
		blogCategoryDAO.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return blogCategoryDAO.count();
	}

	@Override
	public void deleteById(Integer id) {
		blogCategoryDAO.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		blogCategoryDAO.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(BlogCategory entity) {
		blogCategoryDAO.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		blogCategoryDAO.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		blogCategoryDAO.deleteAllInBatch();
	}

	@Override
	public BlogCategory getOne(Integer id) {
		return blogCategoryDAO.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends BlogCategory> entities) {
		blogCategoryDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		blogCategoryDAO.deleteAll();
	}

	@Override
	public BlogCategory getById(Integer id) {
		return blogCategoryDAO.getById(id);
	}

	@Override
	public <S extends BlogCategory> List<S> findAll(Example<S> example) {
		return blogCategoryDAO.findAll(example);
	}

	@Override
	public <S extends BlogCategory> List<S> findAll(Example<S> example, Sort sort) {
		return blogCategoryDAO.findAll(example, sort);
	};
	
	
}
