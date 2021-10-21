package edu.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.dao.CommentDAO;
import edu.poly.entity.Account;
import edu.poly.entity.Comment;
import edu.poly.entity.RateAVG;
import edu.poly.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentDAO commentDAO;

	public CommentServiceImpl(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	@Override
	public <S extends Comment> S save(S entity) {
		return commentDAO.save(entity);
	}

	@Override
	public <S extends Comment> Optional<S> findOne(Example<S> example) {
		return commentDAO.findOne(example);
	}

	@Override
	public Page<Comment> findAll(Pageable pageable) {
		return commentDAO.findAll(pageable);
	}

	@Override
	public List<Comment> findAll() {
		return commentDAO.findAll();
	}

	@Override
	public List<Comment> findAll(Sort sort) {
		return commentDAO.findAll(sort);
	}

	@Override
	public List<Comment> findAllById(Iterable<Integer> ids) {
		return commentDAO.findAllById(ids);
	}

	@Override
	public Optional<Comment> findById(Integer id) {
		return commentDAO.findById(id);
	}

	@Override
	public <S extends Comment> List<S> saveAll(Iterable<S> entities) {
		return commentDAO.saveAll(entities);
	}

	@Override
	public void flush() {
		commentDAO.flush();
	}

	@Override
	public <S extends Comment> S saveAndFlush(S entity) {
		return commentDAO.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return commentDAO.existsById(id);
	}

	@Override
	public <S extends Comment> List<S> saveAllAndFlush(Iterable<S> entities) {
		return commentDAO.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Comment> Page<S> findAll(Example<S> example, Pageable pageable) {
		return commentDAO.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Comment> entities) {
		commentDAO.deleteInBatch(entities);
	}

	@Override
	public <S extends Comment> long count(Example<S> example) {
		return commentDAO.count(example);
	}

	@Override
	public <S extends Comment> boolean exists(Example<S> example) {
		return commentDAO.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Comment> entities) {
		commentDAO.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return commentDAO.count();
	}

	@Override
	public void deleteById(Integer id) {
		commentDAO.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		commentDAO.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Comment entity) {
		commentDAO.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		commentDAO.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		commentDAO.deleteAllInBatch();
	}

	@Override
	public Comment getOne(Integer id) {
		return commentDAO.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Comment> entities) {
		commentDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		commentDAO.deleteAll();
	}

	@Override
	public Comment getById(Integer id) {
		return commentDAO.getById(id);
	}

	@Override
	public <S extends Comment> List<S> findAll(Example<S> example) {
		return commentDAO.findAll(example);
	}

	@Override
	public <S extends Comment> List<S> findAll(Example<S> example, Sort sort) {
		return commentDAO.findAll(example, sort);
	}

	@Override
	public Page<Comment> findByProductId(Integer id, Pageable pageable) {
		return commentDAO.findByProductId(id, pageable);
	}

	@Override
	public Comment create(Comment comment) {
		return commentDAO.save(comment);
	}

	@Override
	public RateAVG rateAVG(Integer id) {
		return commentDAO.rateAVG(id);
	}

	@Override
	public Optional<Comment> getChio(Integer id) {
		// TODO Auto-generated method stub
		return commentDAO.getChio(id);
	}
}
