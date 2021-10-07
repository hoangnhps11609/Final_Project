package edu.poly.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.entity.Blog;
import edu.poly.entity.BlogCategory;
import edu.poly.entity.Gender;
import edu.poly.entity.Product;
import edu.poly.service.BlogCategoryService;
import edu.poly.service.BlogService;
import edu.poly.service.GenderService;

@Controller
public class BlogController {
	@Autowired
	BlogService blogService;
	
	@Autowired
	GenderService genderService;
	
	@Autowired
	BlogCategoryService blogcategoryService;
	
	@RequestMapping("/blog/list")
	public String list(Model model, 
			@RequestParam(name = "cid", required = false) Optional<Integer> cid,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size,
			@RequestParam(name = "search", required = false) String search) {
		
		int categoryID = cid.orElse(0);
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(4);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
		Page<Blog> resultPage = null;
		
		if (categoryID != 0) {
			resultPage = blogService.findByCategoryId(categoryID, pageable);
			model.addAttribute("cid", categoryID);
		} else if (search != null) {
			resultPage = blogService.findByKeyword("%" + search + "%", pageable);
			model.addAttribute("search", search);
		} else {
			resultPage = blogService.findAll(pageable);
		}
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > 5) {
				if (end == totalPages)
					start = end - 5;
				else if (start == 1)
					end = start + 5;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		model.addAttribute("blogPage", resultPage);
		model.addAttribute("size", pageSize);

		
//		List<Blog> list = blogService.findAll();
//		model.addAttribute("items", list);
		return "home/blog";
	}
	
	@RequestMapping("/home/blog-details/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Blog item = blogService.findById(id);
		model.addAttribute("item", item);
		return "home/blog-details";
	}
}
