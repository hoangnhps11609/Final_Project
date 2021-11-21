package edu.poly.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.dao.AccountDAO;
import edu.poly.entity.Account;
import edu.poly.entity.Gender;
import edu.poly.entity.Voucher;
import edu.poly.service.AccountService;
import edu.poly.service.GenderService;
import edu.poly.service.UserService;
import edu.poly.service.VoucherService;
import edu.poly.service.impl.MailerServiceImpl;
import edu.poly.utils.ParamService;
import edu.poly.utils.SessionService;
import net.bytebuddy.utility.RandomString;

@Controller
public class SecurityController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MailerServiceImpl mailer;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	VoucherService vcService;
	
	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("message", "");
		return "security/login";
	}
	
//	@GetMapping("alogin")
//	public String login(ModelMap model) {
//		model.addAttribute("account", new Account());
//		return "security/login";
//	}
//	
//	@PostMapping("alogin")
//	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("account") Account dto, BindingResult result) {
//		if (result.hasErrors()) {
//			return new ModelAndView("security/login", model);
//		}		
//		Account account = accountService.login(dto.getUsername(), dto.getPassword());
//		if (account == null) {
//			model.addAttribute("message", "Invalid username or password!");
//			return new ModelAndView("security/login", model);
//		}
//		session.setAttribute("username", account.getUsername());
//		return new ModelAndView("forward:/", model);
//	}
	
	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message1", "Login successfully!");
		return "home/home";
	}
	
	@RequestMapping("/security/login/error")
	public String loginError(Model model) {
		model.addAttribute("message1", "Login failed!");
		return "security/login";
	}
	
	@RequestMapping("/security/unauthoried")
	public String unauthoried(Model model) {
		return "security/login";
	}
	
	@RequestMapping("/security/logoff/success")
	public String logoffSuccess(Model model) {
		return "home/home";
	}
	
	@RequestMapping("/oauth2/login/success")
	public String success(Model model, OAuth2AuthenticationToken oauth2) throws MessagingException {
		userService.loginFromOAutḥ̣̣2(oauth2);
		String username = request.getRemoteUser();
		Optional<Account> account = accountService.findByUsername(username);
		if (account.isEmpty()) {
			String subject = "Fashi Shop: Created New Account";
			String randomPassword = RandomString.make(6);
			String randomCode = RandomString.make(15);
			Voucher voucher = new Voucher();
			voucher.setStatus(true);
			voucher.setName(randomCode);
			voucher.setValue(10.0);
			String body = "Dear Customer! Welcome to Fashi Fashion Shop. \nWe have created a Account for you. \n" + "Your username: " + username + ".\n Your Password: " + randomPassword + ". And voucher $10: " + randomCode + " is used for Bill than $50!";
			
			Account item = new Account();
			item.setUsername(username);
			item.setPassword(randomPassword);
			item.setFullname("Null Fullname");
			item.setEmail(username);
			item.setPhoto("5aa47c07.png");
			item.setActivated(true);
			item.setPhone(null);
			item.setCreateDate(new Date());
			item.setAddress("Null address");
			vcService.create(voucher);
			accountService.create(item);
			mailer.send(username, subject, body);
		}
		model.addAttribute("message1", "Login successfully!");
		return "home/home";
	}
}
