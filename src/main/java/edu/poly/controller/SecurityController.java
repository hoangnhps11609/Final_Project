package edu.poly.controller;

import java.io.File;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.poly.dao.AccountDAO;
import edu.poly.entity.Account;
import edu.poly.service.impl.MailerServiceImpl;
import edu.poly.utils.ParamService;
import net.bytebuddy.utility.RandomString;

@Controller
public class SecurityController {
	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("message", "Vui lòng đăng nhập!");
		return "security/login";
	}
	
	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công!");
		return "security/login";
	}
	
	@RequestMapping("/security/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "security/login";
	}
	
	@RequestMapping("/security/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message", "Không có quyền truy xuất!");
		return "security/login";
	}
	
	@RequestMapping("/security/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Đăng xuất thành công!");
		return "security/login";
	}
	
	/* ======================================================== */
	@Autowired
	HttpServletRequest request;
	@Autowired
	ServletContext app;
	@Autowired
	AccountDAO adao;
	@Autowired
	ParamService paramService;
	@Autowired
	MailerServiceImpl mailer;
	
	/* ---------------------------- Register ------------------------------*/
	
	@RequestMapping("/security/register/form")
	public String register(Model model) {
		model.addAttribute("message", "Vui lòng nhập thông tin!");
		return "security/register";
	}
	
	/* ---------------------------- Profile ------------------------------*/
	
	@RequestMapping("/security/profile/form")
	public String profile(Model model) {
		String username = request.getRemoteUser();
		Account account = adao.findByUsername(username);
		model.addAttribute("items", account);
		return "security/profile";
	}
	
	@PostMapping("/security/update")
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
		model.addAttribute("message", "Cập nhật thành công");
		return "redirect:/security/profile/form";

	}
	
	/* ---------------------------- ChangePass ------------------------------*/
	
	@RequestMapping("/security/changepass/form")
	public String changepass(Model model) {
		// Gán username
		String username = request.getRemoteUser();
		Account account = adao.findByUsername(username);
		model.addAttribute("items", account);
		model.addAttribute("message", "Vui lòng nhập thông tin!");
		
		String password = paramService.getString("password", "");
		String newpassword= paramService.getString("newpassword", "");
		String confirmpassword= paramService.getString("confirmpassword", "");
		try {
			Account user = adao.findById(username).get();
				if(!user.getPassword().equals(password)) {
					model.addAttribute("message", "Sai password!");
				}else {
					if(newpassword.equals(confirmpassword)) {
						user.setPassword(confirmpassword);
						adao.save(user);
						model.addAttribute("message", "Thay đổi thành công!");
					}else {
						model.addAttribute("message", "Mật khẩu không khớp!");
					}
				}
		} catch (Exception e) {
			model.addAttribute("message", "Lỗi tài khoản!");
		}
		
		return "security/changepass";
	}
	

	/* ---------------------------- ForgotPass ------------------------------*/
	
	@RequestMapping("/security/forgot/form")
	public String forgotpass(Model model) {
		model.addAttribute("message", "Vui lòng nhập thông tin!");
		return "security/forgotpass";
	}
	
	@PostMapping("/security/forgot-password")
	public String sendemail(Model model) {
		String email = paramService.getString("email", "");
		String username = paramService.getString("username", "");
		String subject = "Send your Password!";
		String body = "Your Password: ";
		String password;
		String randomPassword = RandomString.make(6);
		
		try {
			Account user = adao.findById(username).get();
				if(!user.getEmail().equals(email)) {
					model.addAttribute("message", "Sai Email!");
				}else {
					user.setPassword(randomPassword);
					adao.save(user);
					mailer.send(email, subject, body+randomPassword);
					model.addAttribute("message", "Thao tác thành công! Vui lòng kiểm tra mail");
				}
		} catch (Exception e) {
			model.addAttribute("message", "Tài khoản không chính xác!");
		}
		return "security/forgotpass";
	}
	
}
