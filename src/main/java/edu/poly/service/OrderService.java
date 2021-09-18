package edu.poly.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.databind.JsonNode;

import edu.poly.entity.Order;
import edu.poly.entity.OrderDetail;

public interface OrderService {

	Order create(JsonNode orderData);

	Order findById(Long id);

	Page<Order> findByUsername(String username, Pageable pageable);

	List<Order> findAll();

	Order create(Order order);

	Order update(Order order);

	void delete(Long id);

	List<OrderDetail> findByOrder(Long id);

	List<Order> findAll(Sort sort);

	List<Order> findByDate(Date fromFormat, Date toFormat);

	List<Order> findByUsername(String username);
}
