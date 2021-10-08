package edu.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
		Page<Color> colors = cService.findAll(pageable);
		return colors.getContent();
	}
}
