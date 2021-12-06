package edu.poly.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
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

import edu.poly.dao.AccountDAO;
import edu.poly.entity.Account;
import edu.poly.entity.Gender;
import edu.poly.entity.Order;
import edu.poly.service.GenderService;
import edu.poly.service.OrderService;
import edu.poly.entity.Account;
import edu.poly.entity.Gender;
import edu.poly.entity.Order;
import edu.poly.entity.OrderDetail;
import edu.poly.entity.Product;
import edu.poly.entity.ProductDetail;
import edu.poly.service.GenderService;
import edu.poly.service.OrderDetailService;
import edu.poly.service.OrderService;
import edu.poly.service.ProductDetailService;
import edu.poly.service.ProductService;
import edu.poly.service.VoucherService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;

	@Autowired
	OrderDetailService orderDetailService;

	@Autowired
	ProductService productService;

	@Autowired
	GenderService genderService;
	
	@Autowired
	VoucherService voucherService;
	
	@Autowired
	ProductDetailService productDetailService;

	@Autowired
	AccountDAO adao;

	@Autowired
	HttpServletRequest request;

	@RequestMapping("/order/checkout")
	public String checkout(Model model) {
		String username = request.getRemoteUser();
		Account v = adao.findByUsername(username);
		model.addAttribute("items", v);
		return "order/checkout";
	}

	@RequestMapping("/order/list")
	public String list(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam(name = "sid", required = false) Integer sid, HttpServletRequest request) {
		int currentPage = page.orElse(1);
		Pageable pageable = PageRequest.of(currentPage - 1, 9, Sort.by("id").descending());
		Page<Order> resultPage = null;
		String username = request.getRemoteUser();
		resultPage = orderService.findByUsernameandStatus(username, sid, pageable);

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
		model.addAttribute("orderPage", resultPage);
		model.addAttribute("sid", sid);
		Account account = adao.findByUsername(username);
		model.addAttribute("account", account);
		return "order/list";
	}

	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("order", orderService.findById(id));
		return "order/detail";
	}

	@RequestMapping("/order/success/{id}")
	public String success(@PathVariable("id") Long id, Model model, @RequestParam("page") Optional<Integer> page) {
		Page<OrderDetail> OrDe = orderDetailService.findByOrder(id, PageRequest.of(0, 1));
		String OneProInOrder = OrDe.getContent().get(0).getProductDetail().getProduct().getCategory().getId();
		int currentPage = page.orElse(1);
		Pageable pageable = PageRequest.of(currentPage - 1, 9, Sort.by("id").descending());
		Page<Product> resultPage = null;
		resultPage = productService.findByCategoryId(OneProInOrder, pageable);
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
		model.addAttribute("productPage", resultPage);
		model.addAttribute("id", id);
		model.addAttribute("OneProInOrder", OneProInOrder);
		return "order/success";
	}
	
	@RequestMapping("/order/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		Order dto = orderService.findById(id);		
		Order entity = new Order();
		BeanUtils.copyProperties(dto, entity);
		entity.setStatus(4);
		entity.setNoted("Customer cancelled order: " + new Date());
		orderService.save(entity);
		if(dto.getVoucher() != null) {
			dto.getVoucher().setStatus(true);
			voucherService.update(dto.getVoucher());
		}
		List<OrderDetail> listOrDe = orderDetailService.findByOrder(id);
		for(int i=0; i<listOrDe.size(); i++) {
			int OrDeQuan = listOrDe.get(i).getQuantity();
			Long ProDeId = listOrDe.get(i).getProductDetail().getId();
			ProductDetail ProDe = productDetailService.findbyId(ProDeId);
			ProductDetail entityProDe = new ProductDetail();
			//copy từ dto qua entity
			BeanUtils.copyProperties(ProDe, entityProDe);
			entityProDe.setQuantity(entityProDe.getQuantity()+OrDeQuan);
			productDetailService.save(entityProDe);
		}
		return "redirect:/order/list?sid=4";
	}
}
