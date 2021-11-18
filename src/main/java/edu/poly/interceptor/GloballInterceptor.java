package edu.poly.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.entity.Product;
import edu.poly.service.BlogCategoryService;
import edu.poly.service.BlogService;
import edu.poly.service.BrandService;
import edu.poly.service.CategoryService;
import edu.poly.service.ColorService;
import edu.poly.service.GenderService;
import edu.poly.service.ProductService;
import edu.poly.service.SizeService;



@Component
public class GloballInterceptor implements HandlerInterceptor {
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BlogCategoryService blogcateService;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	GenderService genderService;
	
	@Autowired
	SizeService sizeService;

	@Autowired
	ColorService colorService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	BlogService blogService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("cates", categoryService.findAll());
		request.setAttribute("blogcates", blogcateService.findAll());
		request.setAttribute("genderlist", genderService.findAll());
		request.setAttribute("brands", brandService.findAll());
		request.setAttribute("sizes", sizeService.findAll());
		request.setAttribute("colors", colorService.findAll());
		request.setAttribute("blogs", blogService.findAll());
		
		request.setAttribute("wmCloList", productService.findByCategoryIdandGender("1000", 2));
		request.setAttribute("wmHandbagList", productService.findByCategoryIdandGender("1001", 2));
		request.setAttribute("wmShoesList", productService.findByCategoryIdandGender("1002", 2));
		request.setAttribute("wmAccessList", productService.findByCategoryIdandGender("1003", 2));
		
		request.setAttribute("mCloList", productService.findByCategoryIdandGender("1000", 1));
		request.setAttribute("mHandbagList", productService.findByCategoryIdandGender("1001", 1));
		request.setAttribute("mShoesList", productService.findByCategoryIdandGender("1002", 1));
		request.setAttribute("mAccessList", productService.findByCategoryIdandGender("1003", 1));
		
		request.setAttribute("sizesforshoes", sizeService.findSizeByCate("Size For Shoes:" + "%"));
		request.setAttribute("sizesforclothings", sizeService.findSizeByCate("Size For Clothings:" + "%"));
		
	}
}
