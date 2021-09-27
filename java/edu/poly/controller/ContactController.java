package edu.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.entity.Gender;
import edu.poly.service.GenderService;

@Controller
public class ContactController {
	@Autowired
	GenderService genderService;
	
	@RequestMapping("/contact")
	public String blog(Model model) {
		List<Gender> gender = genderService.findAll();
		model.addAttribute("genderlist", gender);
		return "home/contact";
	}
}
