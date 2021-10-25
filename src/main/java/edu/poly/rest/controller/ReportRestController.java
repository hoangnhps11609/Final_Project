package edu.poly.rest.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.entity.Product;
import edu.poly.entity.ProductDetail;
import edu.poly.entity.ReportAccountMonth;
import edu.poly.entity.ReportItemDay;
import edu.poly.entity.ReportItemMonth;
import edu.poly.entity.ReportOrderDay;
import edu.poly.entity.ReportOrderMonth;
import edu.poly.entity.RevenueDay;
import edu.poly.entity.RevenueMonth;
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
	
	//Revenue
	@GetMapping("getRevenue10Day")
	public List<RevenueDay> getRevenue10Day() {
		return orderService.getRevenue10Day(Sort.by("createDate").descending());
	}
	
	@GetMapping("getRevenueMonth")
	public List<RevenueDay> getRevenueMonth() {
		return orderService.getRevenueMonth(Sort.by("createDate").descending());
	}
	
	@GetMapping("getRevenueYear")
	public List<RevenueMonth> getRevenueYear() {
		return orderService.getRevenueYear();
	}
	
	@GetMapping("getRevenueByTime/{from}/{to}")
	public List<RevenueMonth> getRevenueByTime(@PathVariable("from") Date from, @PathVariable("to") Date to) {
		return orderService.getRevenueByTime(from, to);
	}
	
	
	//Account
	@GetMapping("getAccount6Month")
	public List<ReportAccountMonth> getAccount6Month() {
		return accountService.getAccount6Month();
	}
	
	@GetMapping("getAccountByTime/{from}/{to}")
	public List<ReportAccountMonth> getAccountByTime(@PathVariable("from") Date from, @PathVariable("to") Date to) {
		return accountService.getAccountByTime(from, to);
	}
	
	@GetMapping("getAccountNoOrder")
	public List<ReportAccountMonth> getAccountNoOrder() {
		return accountService.getAccountNoOrder();
	}
	
	//Item
	@GetMapping("getItem6Month")
	public List<ReportItemMonth> getItem6Month() {
		return orderService.getItem6Month();
	}
	
	@GetMapping("getItemByTime/{from}/{to}")
	public List<ReportItemMonth> getItemByTime(@PathVariable("from") Date from, @PathVariable("to") Date to) {
		return orderService.getItemByTime(from, to);
	}
	
	@GetMapping("getItemMonth")
	public List<ReportItemDay> getItemMonth() {
		return orderService.getItemMonth(Sort.by("createDate").descending());
	}
	
	//Order
	@GetMapping("getOrderMonth")
	public List<ReportOrderDay> getOrderMonth() {
		return orderService.getOrderMonth(Sort.by("createDate").descending());
	}
	
	@GetMapping("getOrderByTime/{from}/{to}")
	public List<ReportOrderMonth> getOrderByTime(@PathVariable("from") Date from, @PathVariable("to") Date to) {
		return orderService.getOrderByTime(from, to);
	}
	
	@GetMapping("getOrderCancelledYear")
	public List<ReportOrderMonth> getOrderCancelledYear() {
		return orderService.getOrderCancelledYear();
	}
}
