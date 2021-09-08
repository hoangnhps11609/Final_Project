package edu.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
//	@RequestMapping({"/", "/home/index"})
//	public String home() {
//		return "redirect:/product/list";
//	}
///dsadasdsa	
	@RequestMapping({"/", "/home/index"})
	public String home() {
		return "home/home";
	}
	
	@RequestMapping({"/admin", "/admin/home/index"})
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}
}
