package edu.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.entity.Size;
import edu.poly.service.ColorService;
import edu.poly.service.SizeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/sizes")
public class SizeRestController {
	@Autowired
	SizeService cService;
	
	@GetMapping
	public List<Size> getAll(){
		return cService.findAll();
	}
}
