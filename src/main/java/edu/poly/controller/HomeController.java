package edu.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.dao.AccountDAO;
import edu.poly.dao.OrderDetailDAO;
import edu.poly.dao.ProductDAO;
import edu.poly.entity.Product;
import edu.poly.service.ProductService;
import edu.poly.utils.CookieService;
import edu.poly.utils.ParamService;
import edu.poly.utils.SessionService;

@Controller
public class HomeController {
//	@RequestMapping({"/", "/home/index"})
//	public String home() {
//		return "redirect:/product/list";
//	}

	@Autowired
	CookieService cookieService;
	@Autowired
	ParamService paramService;
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ProductService productService;
	
	
	@Autowired
	OrderDetailDAO orderDetailDAO;
	
	@RequestMapping({"/", "/home/index"})
	public String home(Model model) {
		List<Product> wmList = productService.findByCategoryId("1000");
		model.addAttribute("WMitems", wmList);
		List<Product> mList = productService.findByCategoryId("1001");
		model.addAttribute("Mitems", mList);
		return "home/home";
	}
	
	@RequestMapping({"/admin", "/admin/home/index"})
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}
}
