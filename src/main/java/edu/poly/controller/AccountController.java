package edu.poly.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.poly.dao.AccountDAO;
import edu.poly.entity.Account;
import edu.poly.service.AccountService;
import edu.poly.service.impl.MailerServiceImpl;
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
	HttpServletRequest request;

	@RequestMapping("/accounts/newaccount")
	public String registerForm(Model model) {
		model.addAttribute("message","Vui lòng nhập thông tin đăng ký!");
		return "home/register";
	}
	
	
	@RequestMapping("/accounts/info")
	public String infoForm(Model model) {
		String username = request.getRemoteUser();
		Account v = adao.findByUsername(username);
		model.addAttribute("items",v);
		return "home/profile";
		
	}
	
	
	
	@PostMapping("/accounts/update")
	public String Update(Model model, Account item,@RequestParam("img") MultipartFile image) {
		String username = paramService.getString("username", "");
		String password = paramService.getString("password", "");
		String fullname = paramService.getString("fullname", "");
		String email = paramService.getString("email", "");
		Optional<Account> accOp = adao.findById(username);
		String filename = image.getOriginalFilename();
		File file = new File(app.getRealPath("/assets/images/"+filename));
		if(image.isEmpty()) {
			item.setFullname(fullname);
			item.setEmail(email);
			item.setPassword(password);
			item.setUsername(username);
			item.setPhoto(accOp.get().getPhoto());
			adao.save(item);
		}else {
			item.setFullname(fullname);
			item.setEmail(email);
			item.setPassword(password);
			item.setUsername(username);
			item.setPhoto(image.getOriginalFilename());
			adao.save(item);
		}
			model.addAttribute("message","Update Successfully");
			return "redirect:/accounts/info";

	}

}
