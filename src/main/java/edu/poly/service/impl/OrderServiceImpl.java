package edu.poly.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.poly.dao.OrderDAO;
import edu.poly.dao.OrderDetailDAO;
import edu.poly.service.OrderService;
import edu.poly.entity.Order;
import edu.poly.entity.OrderDetail;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderDAO dao;
	
	@Autowired
	OrderDetailDAO ddao;

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.convertValue(orderData, Order.class);
		dao.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		ddao.saveAll(details);
		return order;
	}

	@Override
	public Order findById(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public Page<Order> findByUsername(String username, Pageable pageable) {
		return dao.findByUsername(username, pageable);
	}

	@Override
	public List<Order> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Order> findAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Order create(Order order) {
		return dao.save(order);
	}

	@Override
	public Order update(Order order) {
		return dao.save(order);
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);;
		
	}

	@Override
	public List<OrderDetail> findByOrder(Long id) {
		return ddao.findByOrder(id);
	}

	@Override
	public List<Order> findByDate(Date fromFormat, Date toFormat) {
		return dao.findByDate(fromFormat, toFormat);
	}

	@Override
	public List<Order> findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findbyId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findbyId(String id) {
		// TODO Auto-generated method stub
		return dao.FindById(id);
	}

	@Override
	public Page<Order> findByUsernameandStatus(String username, Integer sid, Pageable pageable) {
		return dao.findByUsernameandStatus(username, sid, pageable);
	}

	@Override
	public <S extends Order> S save(S entity) {
		return dao.save(entity);
	}

}
