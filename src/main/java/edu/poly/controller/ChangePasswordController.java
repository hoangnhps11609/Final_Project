package edu.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.dao.AccountDAO;
import edu.poly.entity.Account;
import edu.poly.utils.ParamService;

@Controller
@RequestMapping("home")
public class ChangePasswordController {

	@Autowired
	ParamService paramService;
	
	@Autowired
	AccountDAO accDAO;
	
	@RequestMapping("change-password")
	public String index() {
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
				}else {
					if(newpassword.equals(confirmpassword)) {
						user.setPassword(confirmpassword);
						accDAO.save(user);
						model.addAttribute("message", "Password is changed!");
					}else {
						model.addAttribute("message", "Password not match!");
					}
				}
		} catch (Exception e) {
			model.addAttribute("message", "Account invalid!");
		}
		return "home/change-password";
	}
}
