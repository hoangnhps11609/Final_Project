package edu.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.entity.Blog;
import edu.poly.entity.BlogCategory;
import edu.poly.service.BlogCategoryService;
import edu.poly.service.BlogService;

@Controller
public class BlogController {
	@Autowired
	BlogService blogService;
	
	@Autowired
	BlogCategoryService blogcategoryService;
	
	@RequestMapping("/blog/list")
	public String list(Model model) {
		List<Blog> list = blogService.findAll();
		List<BlogCategory> blogcates = blogcategoryService.findAll();
		
		model.addAttribute("blogcates", blogcates);
		model.addAttribute("items", list);
		return "home/blog";
	}
}
