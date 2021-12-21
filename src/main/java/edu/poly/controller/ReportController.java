package edu.poly.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.entity.ReportAccountMonth;
import edu.poly.entity.ReportItemDay;
import edu.poly.entity.ReportItemMonth;
import edu.poly.entity.ReportOrderDay;
import edu.poly.entity.ReportOrderMonth;
import edu.poly.entity.RevenueDay;
import edu.poly.entity.RevenueMonth;
import edu.poly.service.AccountService;
import edu.poly.service.OrderService;
import edu.poly.service.ProductService;


@Controller
@RequestMapping("report")
public class ReportController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	AccountService accountService;
	
	/* Revenue */
	@RequestMapping("revenue/10daysAgo")
	public String revennue(Model model) {
		List<RevenueDay> list = orderService.getRevenue10Day(Sort.by("createDate").descending());
		List<Date> day = list.stream().map(x->x.getDay()).collect(Collectors.toList());
		List<Double> revenue = list.stream().map(x->x.getValue()).collect(Collectors.toList());
		List<Double> y = orderService.getTopIn10Day();
		model.addAttribute("xValue", day);
		model.addAttribute("value", revenue);
		model.addAttribute("y", y.get(0)*1.2);
		model.addAttribute("list", list);
		model.addAttribute("yName", "Dollar");
		model.addAttribute("seriesName", "Revenue");
		model.addAttribute("typeName", "Revenue Statistic");
		model.addAttribute("titleName", "Revenue 10 Days Ago");
		return "report/reportDay";
	}
	
	@RequestMapping("revenue/thisMonth")
	public String revennueThisMonth(Model model) {
		List<RevenueDay> list = orderService.getRevenueMonth(Sort.by("createDate").descending());
		List<Date> day = list.stream().map(x->x.getDay()).collect(Collectors.toList());
		List<Double> revenue = list.stream().map(x->x.getValue()).collect(Collectors.toList());
		List<Double> y = orderService.getTopInRevenueMonth();
		model.addAttribute("xValue", day);
		model.addAttribute("value", revenue);
		model.addAttribute("y", y.get(0)*1.2);
		model.addAttribute("yName", "Dollar");
		model.addAttribute("list", list);
		model.addAttribute("seriesName", "Revenue");
		model.addAttribute("typeName", "Revenue Statistic");
		model.addAttribute("titleName", "Revenue This Month");
		return "report/reportDay";
	}
	
	
	@RequestMapping("revenue/thisYear")
	public String revennueThisYear(Model model) {
		List<RevenueMonth> list = orderService.getRevenueYear();
		List<Integer> month = list.stream().map(x->x.getMonth()).collect(Collectors.toList());
		List<Double> revenue = list.stream().map(x->x.getValue()).collect(Collectors.toList());
		List<Double> y = orderService.getTopInRevenueYear();
		model.addAttribute("xValue", month);
		model.addAttribute("value", revenue);
		model.addAttribute("y", y.get(0)*1.2);
		model.addAttribute("yName", "Dollar");
		model.addAttribute("list", list);
		model.addAttribute("seriesName", "Revenue");
		model.addAttribute("typeName", "Revenue Statistic");
		model.addAttribute("titleName", "Revenue This Year");
		return "report/reportMonth";
	}
	
	@RequestMapping("revenue/time/{from}/{to}")
	public String revennueTime(Model model, @PathVariable("from") Date from, @PathVariable("to") Date to) {
		List<RevenueMonth> list = orderService.getRevenueByTime(from, to);
		List<Integer> month = list.stream().map(x->x.getMonth()).collect(Collectors.toList());
		List<Double> revenue = list.stream().map(x->x.getValue()).collect(Collectors.toList());
		List<Double> y = orderService.getTopInRevenueByTime(from, to);
		model.addAttribute("xValue", month);
		model.addAttribute("value", revenue);
		model.addAttribute("y", y.get(0)*1.2);
		model.addAttribute("yName", "Dollar");
		model.addAttribute("list", list);
		model.addAttribute("seriesName", "Revenue");
		model.addAttribute("typeName", "Revenue Statistic");
		model.addAttribute("titleName", "Revenue This Year");
		return "report/reportMonth";
	}
	
	//Account
	@RequestMapping("account/6month")
	public String account6Month(Model model) {
		List<ReportAccountMonth> list = accountService.getAccount6Month();
		List<Integer> month = list.stream().map(x->x.getMonth()).collect(Collectors.toList());
		List<Long> account = list.stream().map(x->x.getValue()).collect(Collectors.toList());
		List<Double> y = accountService.getTopInAccount6Month();
		model.addAttribute("xValue", month);
		model.addAttribute("value", account);
		model.addAttribute("y", y.get(0)*1.2);
		model.addAttribute("yName", "Account");
		model.addAttribute("list", list);
		model.addAttribute("seriesName", "Account");
		model.addAttribute("typeName", "Account Statistic");
		model.addAttribute("titleName", "Account In 6 Months Ago");
		return "report/reportMonth";
	}
	
	@RequestMapping("account/noOrder")
	public String accountNoOrder(Model model) {
		List<ReportAccountMonth> list = accountService.getAccountNoOrder();
		List<Integer> month = list.stream().map(x->x.getMonth()).collect(Collectors.toList());
		List<Long> account = list.stream().map(x->x.getValue()).collect(Collectors.toList());
		List<Double> y = accountService.getTopInAccountNoOrder();
		model.addAttribute("xValue", month);
		model.addAttribute("value", account);
		model.addAttribute("y", y.get(0)*1.2);
		model.addAttribute("yName", "Account");
		model.addAttribute("list", list);
		model.addAttribute("seriesName", "Account");
		model.addAttribute("typeName", "Account Statistic");
		model.addAttribute("titleName", "Account: Don't have any Order In 6 Months Ago");
		return "report/reportMonth";
	}
	
	@RequestMapping("account/time/{from}/{to}")
	public String accountTime(Model model, @PathVariable("from") Date from, @PathVariable("to") Date to) {
		List<ReportAccountMonth> list = accountService.getAccountByTime(from, to);
		List<Integer> month = list.stream().map(x->x.getMonth()).collect(Collectors.toList());
		List<Long> account = list.stream().map(x->x.getValue()).collect(Collectors.toList());
		List<Double> y = accountService.getTopInAccountByTime(from, to);
		model.addAttribute("xValue", month);
		model.addAttribute("value", account);
		model.addAttribute("y", y.get(0)*1.2);
		model.addAttribute("yName", "Account");
		model.addAttribute("list", list);
		model.addAttribute("seriesName", "Account");
		model.addAttribute("typeName", "Account Statistic");
		model.addAttribute("titleName", "Account Created In Time");
		return "report/reportMonth";
	}
	
	
	//Item
	@RequestMapping("item/6month")
	public String item6Month(Model model) {
		List<ReportItemMonth> list = orderService.getItem6Month();
		List<Integer> month = list.stream().map(x->x.getMonth()).collect(Collectors.toList());
		List<Long> value = list.stream().map(x->x.getValue()).collect(Collectors.toList());
		List<Long> y = orderService.getTopInItem6Month();
		model.addAttribute("xValue", month);
		model.addAttribute("value", value);
		model.addAttribute("y", y.get(0)*1.2);
		model.addAttribute("yName", "Item");
		model.addAttribute("list", list);
		model.addAttribute("seriesName", "Item");
		model.addAttribute("typeName", "Item Statistic");
		model.addAttribute("titleName", "Item In 6 Months Ago");
		return "report/reportMonth";
	}
	
	@RequestMapping("item/thisMonth")
	public String itemThisMonth(Model model) {
		List<ReportItemDay> list = orderService.getItemMonth(Sort.by("createDate").descending());
		List<Date> day = list.stream().map(x->x.getDay()).collect(Collectors.toList());
		List<Long> value = list.stream().map(x->x.getValue()).collect(Collectors.toList());
		List<Long> y = orderService.getTopInMonth();
		model.addAttribute("xValue", day);
		model.addAttribute("value", value);
		model.addAttribute("y", y.get(0)*1.2);
		model.addAttribute("yName", "Item");
		model.addAttribute("list", list);
		model.addAttribute("seriesName", "Item");
		model.addAttribute("typeName", "Item Statistic");
		model.addAttribute("titleName", "Item In This Month");
		return "report/reportDay";
	}
	
	@RequestMapping("item/time/{from}/{to}")
	public String itemTime(Model model, @PathVariable("from") Date from, @PathVariable("to") Date to) {
		List<ReportItemMonth> list = orderService.getItemByTime(from, to);
		List<Integer> month = list.stream().map(x->x.getMonth()).collect(Collectors.toList());
		List<Long> value = list.stream().map(x->x.getValue()).collect(Collectors.toList());
		List<Double> y = orderService.getTopInItemByTime(from, to);
		model.addAttribute("xValue", month);
		model.addAttribute("value", value);
		model.addAttribute("y", y.get(0)*1.2);
		model.addAttribute("yName", "Item");
		model.addAttribute("list", list);
		model.addAttribute("seriesName", "Item");
		model.addAttribute("typeName", "Item Statistic");
		model.addAttribute("titleName", "Item In Time");
		return "report/reportMonth";
	}
	
	//Order
	@RequestMapping("order/thisMonth")
	public String orderThisMonth(Model model) {
		List<ReportOrderDay> list = orderService.getOrderMonth(Sort.by("createDate").descending());
		List<Date> day = list.stream().map(x->x.getDay()).collect(Collectors.toList());
		List<Long> value = list.stream().map(x->x.getValue()).collect(Collectors.toList());
		List<Long> y = orderService.getTopInOrderMonth();
		model.addAttribute("xValue", day);
		model.addAttribute("value", value);
		model.addAttribute("y", y.get(0)*1.2);
		model.addAttribute("yName", "Order");
		model.addAttribute("list", list);
		model.addAttribute("seriesName", "Order");
		model.addAttribute("typeName", "Order Statistic");
		model.addAttribute("titleName", "Order In This Month");
		return "report/reportDay";
	}
	
	@RequestMapping("order/time/{from}/{to}")
	public String orderTime(Model model, @PathVariable("from") Date from, @PathVariable("to") Date to) {
		List<ReportOrderMonth> list = orderService.getOrderByTime(from, to);
		List<Integer> month = list.stream().map(x->x.getMonth()).collect(Collectors.toList());
		List<Long> value = list.stream().map(x->x.getValue()).collect(Collectors.toList());
		List<Double> y = orderService.getTopInOrderByTime(from, to);
		model.addAttribute("xValue", month);
		model.addAttribute("value", value);
		model.addAttribute("y", y.get(0)*1.2);
		model.addAttribute("yName", "Order");
		model.addAttribute("list", list);
		model.addAttribute("seriesName", "Order");
		model.addAttribute("typeName", "Order Statistic");
		model.addAttribute("titleName", "Order In Time");
		return "report/reportMonth";
	}
	
	@RequestMapping("order/cancelled")
	public String orderCancelled(Model model) {
		List<ReportOrderMonth> list = orderService.getOrderCancelledYear();
		List<Integer> day = list.stream().map(x->x.getMonth()).collect(Collectors.toList());
		List<Long> value = list.stream().map(x->x.getValue()).collect(Collectors.toList());
		List<Long> y = orderService.getTopInOrderCancelled();
		model.addAttribute("xValue", day);
		model.addAttribute("value", value);
		model.addAttribute("y", y.get(0)*1.2);
		model.addAttribute("yName", "Order");
		model.addAttribute("list", list);
		model.addAttribute("seriesName", "Order");
		model.addAttribute("typeName", "Order Statistic");
		model.addAttribute("titleName", "Order Cancelled In Year");
		return "report/reportMonth";
	}
}
