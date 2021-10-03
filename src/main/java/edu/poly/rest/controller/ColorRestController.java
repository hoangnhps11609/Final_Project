package edu.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.entity.Color;
import edu.poly.service.ColorService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/colors")
public class ColorRestController {
	
	@Autowired
	ColorService cService;
	
	@GetMapping()
	public List<Color> getAll(){
		return cService.findAll();
	}
}
