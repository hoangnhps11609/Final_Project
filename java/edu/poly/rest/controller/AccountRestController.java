package edu.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import edu.poly.service.AccountService;

import java.util.Optional;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	@Autowired
	AccountService accService;
	
	@GetMapping
	public List<Account> getAccounts(@RequestParam("admin")Optional<Boolean> admin){
		if (admin.orElse(false)) {
			return accService.getAdministrators();
		}
		return accService.findAll();
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
		return accService.getAccountByValud(valued);
	}
}
