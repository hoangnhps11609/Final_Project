package edu.poly.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.twilio.exception.ApiException;

import edu.poly.entity.SmsRequest;
import edu.poly.service.SmsService;



@Controller
public class TwilioController {

	@Autowired
   private SmsService smsservice;
	    
	@RequestMapping("/sendSMS")
	public String homepage(ModelAndView model){
		return "index";
	}
	
   @PostMapping("/sendmessage")
   public ResponseEntity<Object> sendmessage(SmsRequest smsrequest){
	   String status=smsservice.sendsms(smsrequest);
	   if("sent".equals(status)||"queued".equals(status)){
       	return new ResponseEntity<Object>("sent successfully",HttpStatus.OK);
       }
	   return new ResponseEntity<Object>("failed to send message",HttpStatus.NOT_FOUND);
   }
   
	
	
}
