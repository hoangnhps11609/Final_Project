package edu.poly.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.poly.dao.AccountDAO;
import edu.poly.entity.Account;
import edu.poly.entity.Order;
import edu.poly.service.AccountService;
import edu.poly.service.OrderService;
import edu.poly.utils.ParamService;

@Controller
public class AccountController {
	@Autowired
	AccountService accountService;

	@Autowired
	ServletContext app;

	@Autowired
	ParamService paramService;

	@Autowired
	AccountDAO adao;
	
	@Autowired
	OrderService orderService;


	@Autowired
	HttpServletRequest request;

	@RequestMapping("/accounts/newaccount")
	public String registerForm(Model model) {
		model.addAttribute("message", "Vui lòng nhập thông tin đăng ký!");
		return "home/register";
	}

	@RequestMapping("/accounts/info")
	public String infoForm(Model model) {
		String username = request.getRemoteUser();
		Account v = adao.findByUsername(username);
		model.addAttribute("items", v);
		model.addAttribute("username", username);
		return "home/profile";

	}

	@PostMapping("/accounts/update")
	public String Update(Model model, Account item,@RequestParam("img") MultipartFile image) {
		String username = paramService.getString("username", "");
		String fullname = paramService.getString("fullname", "");
		String email = paramService.getString("email", "");
		String phone = paramService.getString("phone", "");
		String address = paramService.getString("address", "");
		Optional<Account> accOp = adao.findById(username);
		String filename = image.getOriginalFilename();
		File file = new File(app.getRealPath("/assets/images/"+filename));
		if(image.isEmpty()) {
			item.setFullname(fullname);
			item.setEmail(email);
			item.setPhone(phone);
			item.setAddress(address);
			item.setActivated(true);
			item.setPhoto(accOp.get().getPhoto());
			adao.save(item);
		}else {
			item.setFullname(fullname);
			item.setEmail(email);
			item.setActivated(true);
			item.setPhoto(image.getOriginalFilename());
			item.setPhone(phone);
			item.setAddress(address);
			adao.save(item);
		}
			model.addAttribute("message","Update Successfully");
			return "redirect:/accounts/info";

	}

	@PostMapping("/accounts/register")
	public String register(Model model, @Valid  @ModelAttribute("account") Account item) {
		accountService.create(item);
		model.addAttribute("message", "Update Successfully");
		return "redirect:/security/login/form";

	}

	@PostMapping("/accounts/registerNoACC")
	public String registerNOACC(Model model, Account item, Order order) {
		String username = paramService.getString("username", "");
		String fullname = paramService.getString("fullname", "");
		String email = paramService.getString("email", "");
		item.setFullname(fullname);
		item.setEmail(email);
		item.setPassword("123");
		item.setUsername(username);
		item.setPhoto("noacc.jpg");
		adao.save(item);
		Account account = adao.findByUsername(username);
		model.addAttribute("account", account);
		return "order/checkout";
	}

}
