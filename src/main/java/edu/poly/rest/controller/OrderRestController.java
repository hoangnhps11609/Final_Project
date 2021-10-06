package edu.poly.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import edu.poly.entity.Order;
import edu.poly.entity.OrderDetail;
import edu.poly.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	OrderService orderService;
	
	@PostMapping()
	public Order create(@RequestBody JsonNode orderData) {
		return orderService.create(orderData);
	}
	
	@GetMapping()
	public List<Order> getAll() {
		return orderService.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}	
	
	@GetMapping("/myorder/{id}")
	public List<OrderDetail> getOrderDetail(@PathVariable("id") Long id) {
		return orderService.findByOrder(id);
	}
	
	@GetMapping("/statistic/{from}/{to}")
	public List<Order> getByDate(@PathVariable("from") Date from, @PathVariable("to") Date to){
		return orderService.findByDate(from, to);
	}
	
	@GetMapping("{id}")
	public List<Order> getbyId(@PathVariable("id") Long id) {
		return orderService.findbyId(id);
	}	
	
	@GetMapping("get/{valued}")
	public List<Order> getbyName(@PathVariable("valued") String valued){
		return orderService.findbyId(valued+"%");
	}
}
