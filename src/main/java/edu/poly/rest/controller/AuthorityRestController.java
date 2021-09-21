package edu.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.*;
import edu.poly.authority.service.AuthorityService;
import edu.poly.entity.Authority;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthorityRestController {
	@Autowired
	AuthorityService auService;
	
	@GetMapping
	public List<Authority> findAll(@RequestParam("admin")Optional<Boolean> admin){
		if(admin.orElse(false)) {
			return auService.findAuthoritiesOfAdministrators();
		}
		return auService.findAll();
	}
	
	@PostMapping
	public Authority post(@RequestBody Authority auth) {
		return auService.create(auth);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		auService.delete(id);
	}
}
