package edu.poly.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
	
	List<Order> findbyId(Long id);

	List<Order> findbyId(String id);
	
	Page<Order> findByUsernameandStatus(String username, Integer sid, Pageable pageable);

	<S extends Order> S save(S entity);
	
	Optional<Order> getChio(Long id);
	
	List<Order> findAllWaitingConfirm();
	
	List<Order> findAllConfirmed();
	
	List<Order> findAllShipping();
	
	List<Order> findAllComplete();
	
	List<Order> findAllCancelOrder();

}
