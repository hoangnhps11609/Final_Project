package edu.poly.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.entity.Account;
import edu.poly.entity.CountOrderOfAccount;
import edu.poly.entity.Order;
import edu.poly.service.AccountService;
import edu.poly.service.OrderService;

import java.util.Optional;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	@Autowired
	AccountService accService;
	
	@Autowired
	OrderService orderService;
	
	@GetMapping
	public List<Account> getAccounts(@RequestParam("admin")Optional<Boolean> admin){
		if (admin.orElse(false)) {
			return accService.getAdministrators();
		}
		return accService.findAll(Sort.by("createDate").descending());
	}
	
	//Acc duoc tao trong time
	@GetMapping("/duoctao/{from}/{to}")
	public List<Account> getByDate(@PathVariable("from") Date from, @PathVariable("to") Date to){
		return accService.findByDate(from, to);
	}
	
	@GetMapping("get/{username}")
	public Account getOne(@PathVariable("username") String username) {
		return accService.findById(username);
	}
	
	
	
	@PostMapping
	public Account create(@RequestBody Account account) {
		return accService.create(account);
	}
	
	@PutMapping("{id}")
	public Account update(@PathVariable("id") String id, @RequestBody Account account) {
		return accService.update(account);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") String id) {
		accService.delete(id);
	}
	
	@GetMapping("{valued}")
	public List<Account> getListAccountByValued(@PathVariable("valued") String valued){
		if(valued==null) {
			return accService.findAll();
		}
		return accService.getAccountByValud("%"+valued+"%");	
	}
	
	
//	Account vàng bạc
	@GetMapping("goldencustomer")
	public	List<CountOrderOfAccount> getRankCustomer(){
		List<CountOrderOfAccount> countOrder = accService.getCountOrder();
		return countOrder;
	}
	
//	Account trên 2 năm
	@GetMapping("loyalcustomer")
	public	List<Account> getLoyalCustomer(){
		List<Account> loyalcustomer = accService.getLoyalCustomer();
		return loyalcustomer;
	}
}
