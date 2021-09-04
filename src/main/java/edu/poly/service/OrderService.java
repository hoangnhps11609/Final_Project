package edu.poly.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.databind.JsonNode;

import edu.poly.entity.Order;
import edu.poly.entity.OrderDetail;
import edu.poly.entity.Product;

public interface OrderService {

	Order create(JsonNode orderData);

	Order findById(Long id);

	List<Order> findByUsername(String username);

	List<Order> findAll();

	Order create(Order order);

	Order update(Order order);

	void delete(Long id);

	List<OrderDetail> findByOrder(Long id);

	List<Order> findAll(Sort sort);

	List<Order> findByDate(Date fromFormat, Date toFormat);
}
