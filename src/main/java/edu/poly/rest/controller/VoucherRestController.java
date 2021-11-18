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
	
}