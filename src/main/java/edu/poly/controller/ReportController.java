package edu.poly.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.entity.RevenueDay;
import edu.poly.service.OrderService;
import edu.poly.service.ProductService;


@Controller
@RequestMapping("report")
public class ReportController {

	@Autowired
	OrderService orderService;
	
	/* Revenue */
	@RequestMapping("revenue/10daysAgo")
	public String revennue(Model model) {
		List<RevenueDay> list = orderService.getRevenue10Day(Sort.by("createDate").descending());
		List<Date> day = list.stream().map(x->x.getDay()).collect(Collectors.toList());
		List<Double> revenue = list.stream().map(x->x.getRevenue()).collect(Collectors.toList());
		List<Double> y = orderService.getTopIn10Day();
		model.addAttribute("xValue", day);
		model.addAttribute("value", revenue);
		model.addAttribute("y", y.get(0)*1.2);
		model.addAttribute("yName", "Dollar");
		model.addAttribute("seriesName", "Revenue");
		model.addAttribute("typeName", "Revenue Statistic");
		model.addAttribute("titleName", "Revenue 10 Days Ago");
		return "report/revenue";
	}
	
	@RequestMapping("revenue/thisMonth")
	public String revennueThisMonth(Model model) {
		List<RevenueDay> list = orderService.getRevenueMonth(Sort.by("createDate").descending());
		List<Date> day = list.stream().map(x->x.getDay()).collect(Collectors.toList());
		List<Double> revenue = list.stream().map(x->x.getRevenue()).collect(Collectors.toList());
		List<Double> y = orderService.getTopInRevenueMonth();
		model.addAttribute("xValue", day);
		model.addAttribute("value", revenue);
		model.addAttribute("y", y.get(0)*1.2);
		model.addAttribute("yName", "Dollar");
		model.addAttribute("seriesName", "Revenue");
		model.addAttribute("typeName", "Revenue Statistic");
		model.addAttribute("titleName", "Revenue 10 Days Ago");
		return "report/revenue";
	}
}
