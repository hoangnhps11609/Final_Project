package edu.poly.controller;

import java.io.File;
import java.util.List;
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
import edu.poly.entity.Gender;
import edu.poly.service.GenderService;
import edu.poly.service.impl.MailerServiceImpl;
import edu.poly.utils.ParamService;
import net.bytebuddy.utility.RandomString;

@Controller
public class SecurityController {
	@Autowired
	GenderService genderService;
	
	
	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		List<Gender> gender = genderService.findAll();
		model.addAttribute("genderlist", gender);
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
}
