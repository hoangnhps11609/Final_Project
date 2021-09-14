package edu.poly.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

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

import edu.poly.entity.Order;
import edu.poly.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/order/checkout")
	public String checkout() {
		return "order/checkout";
	}
	@RequestMapping("/order/list")
	public String list(Model model, 
			@RequestParam("page") Optional<Integer> page,
			HttpServletRequest request) {
		int currentPage = page.orElse(1);
		Pageable pageable = PageRequest.of(currentPage-1, 9, Sort.by("createDate"));
		Page<Order> resultPage = null;	
		String username = request.getRemoteUser();
		resultPage = orderService.findByUsername(username, pageable);
		
		int totalPages = resultPage.getTotalPages();
		if(totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			
			if (totalPages > 5) {
				if (end == totalPages) start = end - 5;
				else if(start ==1 ) end = start + 5;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
					.boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		model.addAttribute("orderPage", resultPage);
		return "order/list";
	}
	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("order", orderService.findById(id));
		return "order/detail";
	}
}
