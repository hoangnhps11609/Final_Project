package edu.poly.rest.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import edu.poly.entity.Account;
import edu.poly.entity.Order;
import edu.poly.entity.OrderDetail;
import edu.poly.entity.Product;
import edu.poly.service.OrderDetailService;
import edu.poly.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@PostMapping()
	public Order create(@RequestBody JsonNode orderData) {		
		return orderService.create(orderData);
	}
	
	@GetMapping("revenue")
	public Double getRevenue(){
		Double revenue = orderService.getRevenue();
		return revenue;
	}

	
	@GetMapping()
	public List<Order> getAll() {
		return orderService.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}	
	
	@GetMapping("/myorder/{id}")
	public List<OrderDetail> getOrderDetail(@PathVariable("id") Long id) {
		return orderService.findByOrder(id);
	}
	
	
	@GetMapping("/getorder/{username}")
	public List<Order> getOrder(@PathVariable("username") String username){
		List<Order> orderlist = orderService.findByUsernameandStatus(username, Sort.by(Sort.Direction.DESC, "id"));
		return orderlist;
	}
	
	@GetMapping("/getTotalPro/{username}")
	public Long getTotalPro(@PathVariable("username") String username){
		Long totalPro = orderService.getTotalProByUsernameandStatus(username);
		return totalPro;
	}
	
	@GetMapping("/getTotalBill/{username}")
	public Double getTotalBill(@PathVariable("username") String username){
		Double totalBill = orderService.getTotalBillByUsernameandStatus(username);
		return totalBill;
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
	
	@PutMapping("/info/cashOnDelivery")
	public Order updateIndo(@RequestBody Long id) {
		Double total = orderDetailService.getTotal(id);
		Long quantity = orderDetailService.getQuantity(id);
		Order order = orderService.findById(id);
		order.setQuantity(quantity);
		order.setTotal(total);
		order.setPayment(false);
		Order update = orderService.update(order);
		return update;
	}
	
	@PutMapping("/info/paypal")
	public Order updateInfo(@RequestBody Long id) {
		Double total = orderDetailService.getTotal(id);
		Long quantity = orderDetailService.getQuantity(id);
		Order order = orderService.findById(id);
		order.setQuantity(quantity);
		order.setTotal(total);
		order.setPayment(true);
		Order update = orderService.update(order);
		return update;
	}
	
	@GetMapping("{valued}")
	public List<Order> getbyName(@PathVariable("valued") String valued){
		if(valued==null) {
			return orderService.findAll(Sort.by(Sort.Direction.DESC, "id"));
		}
		return orderService.findbyId("%"+valued+"%", Sort.by(Sort.Direction.DESC, "id"));
	}
	
	@GetMapping("findAllWaitingConfirm")
	public List<Order> findAllWaitingConfirm () {
		return orderService.findAllWaitingConfirm(Sort.by(Sort.Direction.DESC, "id"));
	}
	
	@GetMapping("findAllConfirmed")
	public List<Order> findAllConfirmed () {
		return orderService.findAllConfirmed(Sort.by(Sort.Direction.DESC, "id"));
	}	
	
	@GetMapping("findAllShipping")
	public List<Order> findAllShipping () {
		return orderService.findAllShipping(Sort.by(Sort.Direction.DESC, "id"));
	}	
	
	@GetMapping("findAllComplete")
	public List<Order> findAllComplete () {
		return orderService.findAllComplete(Sort.by(Sort.Direction.DESC, "id"));
	}	
	
	@GetMapping("findAllCancelOrder")
	public List<Order> findAllCancelOrder () {
		return orderService.findAllCancelOrder(Sort.by(Sort.Direction.DESC, "id"));
	}
}
