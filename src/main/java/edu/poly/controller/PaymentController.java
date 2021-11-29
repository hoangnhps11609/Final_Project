package edu.poly.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import edu.poly.config.PaypalPaymentIntent;
import edu.poly.config.PaypalPaymentMethod;
import edu.poly.entity.Order;
import edu.poly.service.OrderService;
import edu.poly.service.PaypalService;
import edu.poly.utils.Utils;

@Controller
public class PaymentController {
	
	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PaypalService paypalService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/pay/{orderId}")
	public String index(Model model, @PathVariable("orderId") Long orderId){
		Order order = orderService.findById(orderId);
		model.addAttribute("totalBill", order.getPay());
		return "payment/index";
	}
	
	@PostMapping("/pay")
	public String pay(HttpServletRequest request, @RequestParam("price") double price ){
		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		try {
			Payment payment = paypalService.createPayment(
					price, 
					"USD", 
					PaypalPaymentMethod.paypal, 
					PaypalPaymentIntent.sale,
					"payment description", 
					cancelUrl, 
					successUrl);
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay(){
		String username = request.getRemoteUser();
		List<Order> listOrderofACC = orderService.findNewOrderByUsername(username);
		Long idNewOrder = listOrderofACC.get(0).getId();
		Order order = orderService.findById(idNewOrder);
		System.out.println(order.getId() + "sad" +  idNewOrder);
		order.setStatus(4);
		order.setNoted("Failed payment: " + new Date());
		orderService.save(order);
		return "payment/cancel";
	}

	@GetMapping(URL_PAYPAL_SUCCESS)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				return "payment/success";
				
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}
	
}
