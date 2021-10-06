package edu.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.dao.BlogDAO;
import edu.poly.entity.Blog;
import edu.poly.entity.Product;
import edu.poly.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService{
	@Autowired
	BlogDAO blogDAO;

	public BlogServiceImpl(BlogDAO blogDAO) {
		this.blogDAO = blogDAO;
	}

	@Override
	public <S extends Blog> S save(S entity) {
		return blogDAO.save(entity);
	}

	@Override
	public <S extends Blog> Optional<S> findOne(Example<S> example) {
		return blogDAO.findOne(example);
	}

	@Override
	public List<Blog> findByCategoryId(Integer cid) {
		return blogDAO.findByCategoryId(cid);
	}
	
	@Override
	public Page<Blog> findByCategoryId(Integer integer, Pageable pageable) {
		return  blogDAO.findByCategoryId(integer, pageable);
	}
	
	@Override
	public Page<Blog> findAll(Pageable pageable) {
		return blogDAO.findAll(pageable);
	}

	@Override
	public List<Blog> findAll() {
		return blogDAO.findAll();
	}

	@Override
	public List<Blog> findAll(Sort sort) {
		return blogDAO.findAll(sort);
	}

	@Override
	public List<Blog> findAllById(Iterable<Integer> ids) {
		return blogDAO.findAllById(ids);
	}

	@Override
	public Blog findById(Integer id) {
		return blogDAO.findById(id).get();
	}

	@Override
	public <S extends Blog> List<S> saveAll(Iterable<S> entities) {
		return blogDAO.saveAll(entities);
	}

	@Override
	public void flush() {
		blogDAO.flush();
	}

	@Override
	public <S extends Blog> S saveAndFlush(S entity) {
		return blogDAO.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return blogDAO.existsById(id);
	}

	@Override
	public <S extends Blog> List<S> saveAllAndFlush(Iterable<S> entities) {
		return blogDAO.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Blog> Page<S> findAll(Example<S> example, Pageable pageable) {
		return blogDAO.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Blog> entities) {
		blogDAO.deleteInBatch(entities);
	}

	@Override
	public <S extends Blog> long count(Example<S> example) {
		return blogDAO.count(example);
	}

	@Override
	public <S extends Blog> boolean exists(Example<S> example) {
		return blogDAO.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Blog> entities) {
		blogDAO.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return blogDAO.count();
	}

	@Override
	public void deleteById(Integer id) {
		blogDAO.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		blogDAO.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Blog entity) {
		blogDAO.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		blogDAO.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		blogDAO.deleteAllInBatch();
	}

	@Override
	public Blog getOne(Integer id) {
		return blogDAO.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Blog> entities) {
		blogDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		blogDAO.deleteAll();
	}

	@Override
	public Blog getById(Integer id) {
		return blogDAO.getById(id);
	}

	@Override
	public <S extends Blog> List<S> findAll(Example<S> example) {
		return blogDAO.findAll(example);
	}

	@Override
	public <S extends Blog> List<S> findAll(Example<S> example, Sort sort) {
		return blogDAO.findAll(example, sort);
	}

	@Override
	public Page<Blog> findByKeyword(String string, Pageable pageable) {
		return blogDAO.findByKeyword(string, pageable);
	}

	@Override
	public Blog create(Blog blog) {
		// TODO Auto-generated method stub
		return blogDAO.save(blog);
	}

	@Override
	public Blog update(Blog blog) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blog> findByName(String valed) {
		// TODO Auto-generated method stub
		return blogDAO.findbyName(valed);
	}
	
	
}
