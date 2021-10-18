package edu.poly.rest.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import edu.poly.entity.Order;
import edu.poly.entity.OrderDetail;
import edu.poly.entity.Product;
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
	
	
	@PutMapping("{id}")
	public Order update2(@PathVariable("id") Long id, @RequestBody Order product) {
		Optional<Order> a = orderService.getChio(id);
		product.setId(id);
		if(a.get().getStatus()==0) {
			product.setStatus(1);
		}else if (a.get().getStatus()==1) {
			product.setStatus(2);
		}else if(a.get().getStatus()==2) {
			product.setStatus(3);
		}
		product.setFullname(a.get().getFullname());
		product.setAddress(a.get().getAddress());
		product.setCreateDate(a.get().getCreateDate());
		product.setPhone(a.get().getPhone());
		return orderService.update(product);
	}
	
	
	
	@GetMapping("{valued}")
	public List<Order> getbyName(@PathVariable("valued") String valued){
		if(valued==null) {
			return orderService.findAll(Sort.by(Sort.Direction.DESC, "id"));
		}
		return orderService.findbyId("%"+valued+"%");
	}
	
	@GetMapping("findAllWaitingConfirm")
	public List<Order> findAllWaitingConfirm () {
		return orderService.findAllWaitingConfirm(Sort.by(Sort.Direction.DESC, "createDate"));
	}
	
	@GetMapping("findAllConfirmed")
	public List<Order> findAllConfirmed () {
		return orderService.findAllConfirmed(Sort.by(Sort.Direction.DESC, "createDate"));
	}	
	
	@GetMapping("findAllShipping")
	public List<Order> findAllShipping () {
		return orderService.findAllShipping(Sort.by(Sort.Direction.DESC, "createDate"));
	}	
	
	@GetMapping("findAllComplete")
	public List<Order> findAllComplete () {
		return orderService.findAllComplete(Sort.by(Sort.Direction.DESC, "createDate"));
	}	
	
	@GetMapping("findAllCancelOrder")
	public List<Order> findAllCancelOrder () {
		return orderService.findAllCancelOrder(Sort.by(Sort.Direction.DESC, "createDate"));
	}
}
