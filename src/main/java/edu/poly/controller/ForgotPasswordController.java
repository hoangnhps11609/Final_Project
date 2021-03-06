package edu.poly.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.dao.AccountDAO;
import edu.poly.entity.Account;
import edu.poly.entity.Gender;
import edu.poly.service.GenderService;
import edu.poly.service.impl.MailerServiceImpl;
import edu.poly.utils.ParamService;
import edu.poly.utils.SessionService;
import net.bytebuddy.utility.RandomString;

@Controller
@RequestMapping("home")
public class ForgotPasswordController {

	@Autowired
	AccountDAO dao;
	@Autowired
	SessionService session;
	@Autowired
	ParamService paramService;
	@Autowired
	MailerServiceImpl mailer;
	@Autowired
	ServletContext app;

	@Autowired
	GenderService genderService;
	
	@RequestMapping("forgot-password")
	public String index(Model model) {
		return "home/forgot-password";
	}
	
	@PostMapping("forgot-password")
	public String change(Model model) {
		String email = paramService.getString("email", "");
		String username = paramService.getString("username", "");
		Account user = dao.findById(username).get();
		String subject = "Fashi Shop: Send your Password";
		String randomPassword = RandomString.make(6);
		String body = "Dear " + user.getFullname() + "!\n" + "Thank you for contacting Fashi Fashion Shop. We are pleased to announce that your new password is: " + randomPassword;
		try {
			if(!user.getEmail().equals(email)) {
				model.addAttribute("message", "Wrong Email!");
			}else {
				user.setPassword(randomPassword);
				dao.save(user);
				mailer.send(email, subject, body);
				model.addAttribute("message", "Please check your Email!");
				return "security/login";
			}
		} catch (Exception e) {
			model.addAttribute("message", "Account invalid!");
			return "home/forgot-password";
		}		
		return null;
	}
}
