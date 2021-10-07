package edu.poly.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.service.BlogCategoryService;
import edu.poly.service.BrandService;
import edu.poly.service.CategoryService;
import edu.poly.service.ColorService;
import edu.poly.service.GenderService;
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
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("cates", categoryService.findAll());
		request.setAttribute("blogcates", blogcateService.findAll());
		request.setAttribute("genderlist", genderService.findAll());
		request.setAttribute("brands", brandService.findAll());
		request.setAttribute("sizes", sizeService.findAll());
		request.setAttribute("colors", colorService.findAll());
		request.setAttribute("sizesforshoes", sizeService.findSizeByCate("Size For Shoes:" + "%"));
		request.setAttribute("sizesforclothings", sizeService.findSizeByCate("Size For Clothings:" + "%"));
	}
}
