package edu.poly.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CookieService {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	
	
	//public Cookie get(String name) {…}
	
	//public String getValue(String name) {…}
	
	public Cookie add(String name, String value, int hours) {
		Cookie cookie = new Cookie(name,value);
		cookie.setMaxAge(hours);
		response.addCookie(cookie);
		return cookie;
	}
	
	public void remove(String name) {
		
	}




}
