package edu.poly.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import edu.poly.entity.ReportItemDay;
import edu.poly.entity.ReportItemMonth;
import edu.poly.entity.ReportOrderDay;
import edu.poly.entity.ReportOrderMonth;
import edu.poly.entity.RevenueDay;
import edu.poly.entity.RevenueMonth;

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

	@Override
	public Optional<Order> getChio(Long id) {
		// TODO Auto-generated method stub
		return dao.getChio(id);
	}

	@Override
	public List<Order> findAllWaitingConfirm(Sort sort) {
		// TODO Auto-generated method stub
		return dao.findAllWaitingConfirm(sort);
	}

	@Override
	public List<Order> findAllConfirmed(Sort sort) {
		// TODO Auto-generated method stub
		return dao.findAllConfirmed(sort);
	}

	@Override
	public List<Order> findAllShipping(Sort sort) {
		// TODO Auto-generated method stub
		return dao.findAllShipping(sort);
	}

	@Override
	public List<Order> findAllComplete(Sort sort) {
		// TODO Auto-generated method stub
		return dao.findAllComplete(sort);
	}

	@Override
	public List<Order> findAllCancelOrder(Sort sort) {
		// TODO Auto-generated method stub
		return dao.findAllCancelOrder(sort);
	}

	@Override
	public List<Order> findByUsernameandStatus(String username, Sort sort) {
		return dao.findByUsernameandStatus(username, sort);
	}

	@Override
	public Double getRevenue() {
		return dao.getRevenue();
	}

	@Override
	public Long getSales() {
		return dao.getSales();
	}

	@Override
	public Long countOrders() {
		return dao.countOrders();
	}

	@Override
	public List<RevenueDay> getRevenue10Day(Sort sort) {
		return dao.getRevenue10Day(sort);
	}

	@Override
	public List<RevenueDay> getRevenueMonth(Sort sort) {
		return dao.getRevenueMonth(sort);
	}

	@Override
	public List<RevenueMonth> getRevenueYear() {
		return dao.getRevenueYear();
	}

	@Override
	public List<RevenueMonth> getRevenueByTime(Date from, Date to) {
		return dao.getRevenueByTime(from, to);
	}

	@Override
	public List<ReportItemMonth> getItem6Month() {
		return dao.getItem6Month();
	}

	@Override
	public List<ReportItemMonth> getItemByTime(Date from, Date to) {
		return dao.getItemByTime(from, to);
	}

	@Override
	public List<ReportItemDay> getItemMonth(Sort sort) {
		return dao.getItemMonth(sort);
	}

	@Override
	public List<ReportOrderDay> getOrderMonth(Sort sort) {
		return dao.getOrderMonth(sort);
	}

	@Override
	public List<ReportOrderMonth> getOrderByTime(Date from, Date to) {
		return dao.getOrderByTime(from, to);
	}

	@Override
	public List<ReportOrderMonth> getOrderCancelledYear() {
		return dao.getOrderCancelledYear();
	}

	@Override
	public List<Double> getTopIn10Day() {
		return dao.getTopInDay();
	}

	@Override
	public List<Double> getTopInRevenueMonth() {
		return dao.getTopInRevenueMonth();
	}


}
