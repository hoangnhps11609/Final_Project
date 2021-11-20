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
import net.bytebuddy.utility.RandomString;

import java.util.Optional;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/vouchers")
public class VoucherRestController {
	@Autowired
	VoucherService vcService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	private SmsService smsservice;

	@GetMapping()
	public List<Voucher> getByDate(){
		return vcService.findAll(Sort.by("id").descending());
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
	public Voucher create(@RequestBody Voucher voucher) {
		String randomCode = RandomString.make(15);
		voucher.setStatus(true);
		voucher.setName(randomCode);
		return vcService.create(voucher);
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
	
	@GetMapping("/sendVoucherHPBD")
	public List<Account> sendVoucherHPBD() {
		List<Account> listacc = accountService.listHPBD(new Date());
		for(int i=0; i<listacc.size(); i++) {
			String randomCode = RandomString.make(15);
			Voucher voucher = new Voucher();
			voucher.setStatus(true);
			voucher.setName(randomCode);
			voucher.setValue(10.0);
			vcService.create(voucher);
			Account account = accountService.findById(listacc.get(i).getUsername());
			String beginNumberPhone = account.getPhone().substring(0, 3);
			if (!beginNumberPhone.equals("090")||!beginNumberPhone.equals("093")||!beginNumberPhone.equals("089")||!beginNumberPhone.equals("070")||!beginNumberPhone.equals("079")||!beginNumberPhone.equals("078")||!beginNumberPhone.equals("077")||!beginNumberPhone.equals("076")) {
				String phoneNumber = "+84" + account.getPhone().substring(1);
				SmsRequest sms = new SmsRequest(phoneNumber, "Fashi Fashion Shop: Hi " + account.getFullname() + ", Happy Birthday to you, we sended a Voucher $10. " + "Voucher Code: " + randomCode + ". Thanks you for buying in Fashi!");
				smsservice.sendsms(sms);
			}
		}
		return null;
	}
}
