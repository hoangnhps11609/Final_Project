package edu.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.dao.OrderDetailDAO;
import edu.poly.entity.OrderDetail;
import edu.poly.service.OrderDetailService;
@Service
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	OrderDetailDAO orderDetailDAO;

	public OrderDetailServiceImpl(OrderDetailDAO orderDetailDAO) {
		this.orderDetailDAO = orderDetailDAO;
	}

	@Override
	public Page<OrderDetail> findByOrder(Long id, Pageable pageable) {
		return orderDetailDAO.findByOrder(id, pageable);
	}

	@Override
	public <S extends OrderDetail> S save(S entity) {
		return orderDetailDAO.save(entity);
	}

	@Override
	public <S extends OrderDetail> Optional<S> findOne(Example<S> example) {
		return orderDetailDAO.findOne(example);
	}

	@Override
	public Page<OrderDetail> findAll(Pageable pageable) {
		return orderDetailDAO.findAll(pageable);
	}

	@Override
	public List<OrderDetail> findAll() {
		return orderDetailDAO.findAll();
	}

	@Override
	public List<OrderDetail> findAll(Sort sort) {
		return orderDetailDAO.findAll(sort);
	}

	@Override
	public List<OrderDetail> findAllById(Iterable<Long> ids) {
		return orderDetailDAO.findAllById(ids);
	}

	@Override
	public Optional<OrderDetail> findById(Long id) {
		return orderDetailDAO.findById(id);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
		return orderDetailDAO.saveAll(entities);
	}

	@Override
	public void flush() {
		orderDetailDAO.flush();
	}

	@Override
	public <S extends OrderDetail> S saveAndFlush(S entity) {
		return orderDetailDAO.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return orderDetailDAO.existsById(id);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
		return orderDetailDAO.saveAllAndFlush(entities);
	}

	@Override
	public <S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orderDetailDAO.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<OrderDetail> entities) {
		orderDetailDAO.deleteInBatch(entities);
	}

	@Override
	public <S extends OrderDetail> long count(Example<S> example) {
		return orderDetailDAO.count(example);
	}

	@Override
	public <S extends OrderDetail> boolean exists(Example<S> example) {
		return orderDetailDAO.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<OrderDetail> entities) {
		orderDetailDAO.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return orderDetailDAO.count();
	}

	@Override
	public void deleteById(Long id) {
		orderDetailDAO.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		orderDetailDAO.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(OrderDetail entity) {
		orderDetailDAO.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		orderDetailDAO.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		orderDetailDAO.deleteAllInBatch();
	}

	@Override
	public OrderDetail getOne(Long id) {
		return orderDetailDAO.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends OrderDetail> entities) {
		orderDetailDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		orderDetailDAO.deleteAll();
	}

	@Override
	public OrderDetail getById(Long id) {
		return orderDetailDAO.getById(id);
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example) {
		return orderDetailDAO.findAll(example);
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort) {
		return orderDetailDAO.findAll(example, sort);
	}
	
	
}
