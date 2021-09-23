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
import org.springframework.web.bind.annotation.RestController;

import edu.poly.entity.Voucher;
import edu.poly.service.VoucherService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/vouchers")
public class VoucherRestController {
	@Autowired
	VoucherService vService;
	
	@GetMapping()
	public List<Voucher> getAll() {
		return vService.findAll();
	}
	
	@GetMapping("{id}")
	public Voucher getOne(@PathVariable("id") Integer id) {
		return vService.getById(id);
	}
	
	@PostMapping
	public Voucher create(@RequestBody Voucher product) {
		return vService.save(product);
	}
	
	@PutMapping("{id}")
	public Voucher update(@PathVariable("id") Integer id, @RequestBody Voucher product) {
		return vService.update(product);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		vService.deleteById(id);
	}
}
