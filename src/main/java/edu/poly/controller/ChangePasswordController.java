package edu.poly.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.dao.AccountDAO;
import edu.poly.entity.Account;
import edu.poly.entity.Gender;
import edu.poly.service.AccountService;
import edu.poly.service.GenderService;
import edu.poly.utils.ParamService;

@Controller
@RequestMapping("home")
public class ChangePasswordController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	ServletContext app;

	@Autowired
	GenderService genderService;
	
	@Autowired
	AccountDAO adao;
	
	@Autowired
	HttpServletRequest request;
	@Autowired
	
	ParamService paramService;
	
	@Autowired
	AccountDAO accDAO;
	
	@RequestMapping("change-password")
	public String index(Model model) {
		String username = request.getRemoteUser();
		Account v = adao.findByUsername(username);
		model.addAttribute("items",v);
		return "home/change-password";
	}
	
	@PostMapping("change-password")
	public String change(Model model) {
		String username = paramService.getString("username", "");
		String password = paramService.getString("password", "");
		String newpassword= paramService.getString("newpassword", "");
		String confirmpassword= paramService.getString("confirmpassword", "");
		try {
			Account user = accDAO.findById(username).get();
				if(!user.getPassword().equals(password)) {
					model.addAttribute("message", "Wrong Password!");
					return "redirect:/home/change-password";
				}else {
					if(newpassword.equals(confirmpassword)) {
						user.setPassword(confirmpassword);
						accDAO.save(user);
						model.addAttribute("message", "Password is changed!");
						return "redirect:/security/logoff";
					}else {
						model.addAttribute("message", "Password not match!");
						return "redirect:/home/change-password";
					}
				}
		} catch (Exception e) {
			model.addAttribute("message", "Account invalid!");
		}
		return "redirect:/security/logoff";
	}
	
	

}
