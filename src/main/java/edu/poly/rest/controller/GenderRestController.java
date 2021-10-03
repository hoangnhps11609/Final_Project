package edu.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.entity.Brand;
import edu.poly.entity.Category;
import edu.poly.entity.Gender;
import edu.poly.service.BrandService;
import edu.poly.service.CategoryService;
import edu.poly.service.GenderService;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/genders")
public class GenderRestController {
	@Autowired
	GenderService gService;
	
	@GetMapping()
	public List<Gender> getAll() {
		return gService.findAll();
	}
	
}
