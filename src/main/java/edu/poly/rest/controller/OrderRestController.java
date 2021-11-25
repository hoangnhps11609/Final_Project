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
import edu.poly.entity.SmsRequest;
import edu.poly.entity.Voucher;
import edu.poly.service.OrderDetailService;
import edu.poly.service.OrderService;
import edu.poly.service.SmsService;
import edu.poly.service.VoucherService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	private SmsService smsservice;
	
	@Autowired
	VoucherService vcService;

	
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
		return orderService.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
	}	
	
	@GetMapping("/myorder/{id}")
	public List<OrderDetail> getOrderDetail(@PathVariable("id") Long id) {
		return orderService.findByOrder(id);
	}
	
	
	@GetMapping("/getorder/{username}")
	public List<Order> getOrder(@PathVariable("username") String username){
		List<Order> orderlist = orderService.findByUsernameandStatus(username, Sort.by(Sort.Direction.DESC, "createDate"));
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
	public Order update2(@PathVariable("id") Long id, @RequestBody Order order) {
		Optional<Order> a = orderService.getChio(id);
		String beginNumberPhone = a.get().getPhone().substring(0, 3);
		order.setId(id);
		if(a.get().getStatus()==0) {
			order.setStatus(1);
		}else if (a.get().getStatus()==1) {
			if (beginNumberPhone.equals("090")||beginNumberPhone.equals("093")||beginNumberPhone.equals("089")||beginNumberPhone.equals("070")||beginNumberPhone.equals("079")||beginNumberPhone.equals("078")||beginNumberPhone.equals("077")||beginNumberPhone.equals("076")) {
				
				order.setStatus(2);  
				order.setFullname(a.get().getFullname());
				order.setAddress(a.get().getAddress());
				order.setCreateDate(a.get().getCreateDate());
				order.setPhone(a.get().getPhone());
				return orderService.update(order);
			} else {
				String phoneNumber = "+84" + a.get().getPhone().substring(1);
				SmsRequest sms = new SmsRequest(phoneNumber, "Fashi Fashion Shop: Hi " + a.get().getFullname() + ", Your Order now is shipping to address '" + a.get().getAddress() + "'. Thanks you for buying in Fashi!");
				String status=smsservice.sendsms(sms);
				if("sent".equals(status)||"queued".equals(status)){
					order.setStatus(2);  
					order.setFullname(a.get().getFullname());
					order.setAddress(a.get().getAddress());
					order.setCreateDate(a.get().getCreateDate());
					order.setPhone(a.get().getPhone());
					return orderService.update(order);
				}
			}
		}else if(a.get().getStatus()==2) {
			order.setStatus(3);
		}
		order.setFullname(a.get().getFullname());
		order.setAddress(a.get().getAddress());
		order.setCreateDate(a.get().getCreateDate());
		order.setPhone(a.get().getPhone());
		return orderService.update(order);
	}
	
	@PutMapping("/info/cashOnDelivery/{voucher}")
	public Order updateIndo(@RequestBody Long id, @PathVariable("voucher") String voucher) {
		Voucher vouchers = vcService.getVoucher(voucher);
		Double total = orderDetailService.getTotal(id);
		Long quantity = orderDetailService.getQuantity(id);
		Order order = orderService.findById(id);
		order.setPay(total-vouchers.getValue());
		order.setTotal(total);
		order.setVoucher(vouchers);
		order.setQuantity(quantity);
		order.setPayment(false);
		Order update = orderService.update(order);
		vouchers.setStatus(false);
		Voucher updateVoucher = vcService.update(vouchers);
		return update;
	}
	
	@PutMapping("/info/cashOnDelivery")
	public Order updateBt(@RequestBody Long id) {
		Double total = orderDetailService.getTotal(id);
		Long quantity = orderDetailService.getQuantity(id);
		Order order = orderService.findById(id);
		order.setTotal(total);
		order.setPay(total);
		order.setVoucher(null);
		order.setQuantity(quantity);
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
		order.setPay(total);
		order.setVoucher(null);
		order.setTotal(total);
		order.setPayment(true);
		Order update = orderService.update(order);
		return update;
	}
	
	@PutMapping("/info/paypal/{voucher}")
	public Order updateInfo(@RequestBody Long id, @PathVariable("voucher") String voucher) {
		Voucher vouchers = vcService.getVoucher(voucher);
		Double total = orderDetailService.getTotal(id);
		Long quantity = orderDetailService.getQuantity(id);
		Order order = orderService.findById(id);
		order.setQuantity(quantity);
		order.setTotal(total);
		order.setVoucher(vouchers);
		order.setPay(total-vouchers.getValue());
		order.setPayment(true);
		Order update = orderService.update(order);
		vouchers.setStatus(false);
		Voucher updateVoucher = vcService.update(vouchers);
		return update;
	}
	
	@GetMapping("{valued}")
	public List<Order> getbyName(@PathVariable("valued") String valued){
		if(valued==null) {
			return orderService.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
		}
		return orderService.findbyId("%"+valued+"%", Sort.by(Sort.Direction.DESC, "createDate"));
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
	
	
	@GetMapping("/vouchers/{name}")
	public  Voucher getVoucher(@PathVariable("name") String name) {
		return vcService.getVoucher(name);
	}
	
	@PutMapping("/cancalledOrder/{id}")
	public Order cancelledOrder(@PathVariable("id") Long id, @RequestBody Order order) {
		order.setStatus(4);
		orderService.update(order);
		return null;
	}
}
