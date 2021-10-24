package edu.poly.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.entity.Product;
import edu.poly.entity.ProductDetail;
import edu.poly.service.AccountService;
import edu.poly.service.OrderService;
import edu.poly.service.ProductDetailService;
import edu.poly.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/reports")
public class ReportRestController {
	
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("revenue")
	public Double revenue() {
		return orderService.getRevenue();
	}
	
	@GetMapping("sales")
	public Long sales() {
		return orderService.getSales();
	}
	
	@GetMapping("orders")
	public Long orders() {
		return orderService.countOrders();
	}
	
	@GetMapping("customers")
	public Long customers() {
		return accountService.countCustomers();
	}
	
	


}
