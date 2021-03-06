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
import edu.poly.entity.SmsRequest;
import edu.poly.entity.Voucher;
import edu.poly.service.AccountService;
import edu.poly.service.OrderService;
import edu.poly.service.SmsService;
import edu.poly.service.VoucherService;
import edu.poly.service.impl.MailerServiceImpl;
import net.bytebuddy.utility.RandomString;

import java.util.Optional;

import javax.mail.MessagingException;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	@Autowired
	AccountService accService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	private SmsService smsservice;
	
	@Autowired
	VoucherService vcService;
	
	@Autowired
	MailerServiceImpl mailer;
	
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
		Account acc = accService.findById(username);
		return acc;
	}
	
	
	@GetMapping("getValidation/{validation}")
	public List<Account> getUsername(@PathVariable("validation") String validation) {
		List<Account> acc = accService.findValidation(validation);
		return acc;
	}
	
	
	@PostMapping
	public Account create(@RequestBody Account account) throws MessagingException {
		Account newAccount = accService.create(account);
		String randomCode = RandomString.make(15);
		Voucher voucher = new Voucher();
		voucher.setStatus(true);
		voucher.setName(randomCode);
		voucher.setValue(10.0);
		vcService.create(voucher);
		String beginNumberPhone = account.getPhone().substring(0, 3);
		if (beginNumberPhone.equals("090")||beginNumberPhone.equals("093")||beginNumberPhone.equals("089")||beginNumberPhone.equals("070")||beginNumberPhone.equals("079")||beginNumberPhone.equals("078")||beginNumberPhone.equals("077")||beginNumberPhone.equals("076")) {
			String subject = "Fashi Shop: Created New Account";
			String body = "Dear Customer! Welcome to Fashi Fashion Shop. \nWe have created a Account for you. \n" + "Your username: " + account.getUsername() + ".\n Your Password: " + account.getPassword() + ". And voucher $10: " + randomCode + " is used for Bill than $50!";
			mailer.send(account.getEmail(), subject, body);
		}else {
			String phoneNumber = "+84" + account.getPhone().substring(1);
			SmsRequest sms = new SmsRequest(phoneNumber, "Fashi Fashion Shop: Hi " + account.getFullname() + ", Welcome to Fashi, we sended a Voucher $10 for new account. " + "Voucher Code: " + randomCode + " is used for Bill than $50. Thanks you for buying in Fashi!");
			smsservice.sendsms(sms);
		}
		return newAccount;
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
	
	
//	Account v??ng b???c
	@GetMapping("goldencustomer")
	public	List<CountOrderOfAccount> getRankCustomer(){
		List<CountOrderOfAccount> countOrder = accService.getCountOrder();
		return countOrder;
	}
	
//	Account tr??n 2 n??m
	@GetMapping("loyalcustomer")
	public	List<Account> getLoyalCustomer(){
		List<Account> loyalcustomer = accService.getLoyalCustomer();
		return loyalcustomer;
	}
}
