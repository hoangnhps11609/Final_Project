package edu.poly.config;

import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

import edu.poly.entity.TwilioProperties;

@Configuration
public class TwilioInitializer {

private final TwilioProperties twilioproperties;
	
	public TwilioInitializer(TwilioProperties twilioproperties){
		this.twilioproperties=twilioproperties;
		Twilio.init(twilioproperties.getAccountSid(), twilioproperties.getAuthToken());
		System.out.println("twilio intialized with account: "+twilioproperties.getAccountSid());
	}
}
