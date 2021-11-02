package edu.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.dao.OrderDetailDAO;
import edu.poly.entity.Blog;
import edu.poly.entity.Brand;
import edu.poly.entity.Category;
import edu.poly.entity.Color;
import edu.poly.entity.Gender;
import edu.poly.entity.Product;
import edu.poly.entity.Size;
import edu.poly.service.BlogService;
import edu.poly.service.BrandService;
import edu.poly.service.CategoryService;
import edu.poly.service.ColorService;
import edu.poly.service.GenderService;
import edu.poly.service.ProductService;
import edu.poly.service.SizeService;
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
	GenderService genderService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BrandService brandService;	
	
	@Autowired
	ColorService colorService;
	
	@Autowired
	SizeService sizeService;
	
	@Autowired
	OrderDetailDAO orderDetailDAO;
	
	@Autowired
	ParamService paramService;
	
	@Autowired
	BlogService blogService;
	
	@RequestMapping({"/", "/home/index"})
	public String home(Model model) {
		List<Product> wmCloList = productService.findByCategoryIdandGender("1000", 2);
		model.addAttribute("wmCloList", wmCloList);
		List<Product> wmHandbagList = productService.findByCategoryIdandGender("1001", 2);
		model.addAttribute("wmHandbagList", wmHandbagList);
		List<Product> wmShoesList = productService.findByCategoryIdandGender("1002", 2);
		model.addAttribute("wmShoesList", wmShoesList);
		List<Product> wmAccessList = productService.findByCategoryIdandGender("1003", 2);
		model.addAttribute("wmAccessList", wmAccessList);
		
		
		
		
		List<Product> mCloList = productService.findByCategoryIdandGender("1000", 1);
		model.addAttribute("mCloList", mCloList);
		List<Product> mHandbagList = productService.findByCategoryIdandGender("1001", 1);
		model.addAttribute("mHandbagList", mHandbagList);
		List<Product> mShoesList = productService.findByCategoryIdandGender("1002", 1);
		model.addAttribute("mShoesList", mShoesList);
		List<Product> mAccessList = productService.findByCategoryIdandGender("1003", 1);
		model.addAttribute("mAccessList", mAccessList);
		
		List<Blog> blog = blogService.findAll();
		model.addAttribute("blogs", blog);
		
		return "home/home";
	}
	
	@RequestMapping({"/admin", "/admin/home/index"})
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}
	
	
//	@RequestMapping({"/", "/home/index"})
//	public String home(Model model,
//			@RequestParam(name="cid", required = false) String cid) {
//		
//		
//		
//		if(cid!=null) {
//			List<Product> wmList = productService.findByCategoryIdandGender(cid, 0);
//			model.addAttribute("wmList", wmList);
//		}else {
//			List<Product> wmList = productService.findAll();
//			model.addAttribute("wmList", wmList);
//		}
//		
//		
//		
//		List<Product> mList = productService.findByCategoryId("1001");
//		model.addAttribute("Mitems", mList);
//		List<Category> cate = categoryService.findAll();
//		model.addAttribute("cate", cate);
//		return "home/home";
//	}
}
