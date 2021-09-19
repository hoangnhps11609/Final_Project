package edu.poly.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.poly.service.ProductService;


@Controller
public class ReportController {
	@Autowired
	ProductService se;
	
	@GetMapping("/admin/report/report1")
	public String getAllEmployee(Model model) {	
		
	List<String> nameList= se.getReport().stream().map(x->x.getCak().getName()).collect(Collectors.toList());
	List<Long> ageList = se.getReport().stream().map(x-> x.getCount()).collect(Collectors.toList());
	List<Double> sumList = se.getReport().stream().map(x->x.getSum()).collect(Collectors.toList());
	model.addAttribute("sum", sumList);
	model.addAttribute("name", nameList);
	model.addAttribute("age", ageList);
	return "foward:report/index";
	
	}
	
}
