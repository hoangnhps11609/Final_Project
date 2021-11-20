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
import edu.poly.entity.Voucher;
import edu.poly.service.AccountService;
import edu.poly.service.OrderService;
import edu.poly.service.VoucherService;

import java.util.Optional;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/vouchers")
public class VoucherRestController {
	@Autowired
	VoucherService vcService;

	@GetMapping()
	public List<Voucher> getByDate(){
		return vcService.findAll();
	}
	
	@GetMapping("/getnewvoucher")
	public List<Voucher> getNewVoucher(){
		return vcService.getNewVoucher();
	}
	
	@GetMapping("/getusedvoucher")
	public List<Voucher> getUsedVoucher(){
		return vcService.getUsedVoucher();
	}
	
	@GetMapping("/findby/{keyword}")
	public List<Voucher> findByKeyword(@PathVariable("keyword") String keyword){
		return vcService.findVoucherByKeyword("%"+keyword+"%");
	}
	
	@PostMapping
	public Voucher create(@RequestBody Voucher account) {
		return vcService.create(account);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		vcService.delete(id);
	}
	
	@PutMapping("{id}")
	public Voucher update(@PathVariable("id") Integer id, @RequestBody Voucher voucher) {
		return vcService.update(voucher);
	}
	
	@GetMapping("getValidation/{validation}")
	public List<Voucher> getVCName(@PathVariable("validation") String validation) {
		List<Voucher> vc = vcService.findVoucherByName(validation);
		return vc;
	}
	
	
	@GetMapping("{name}")
	public  Voucher getVoucher(@PathVariable("name") String name) {
		return vcService.getVoucher(name);
	}
}
