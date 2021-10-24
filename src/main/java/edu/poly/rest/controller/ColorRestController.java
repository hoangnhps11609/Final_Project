package edu.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.entity.BrandInventory;
import edu.poly.entity.BrandTop;
import edu.poly.entity.Category;
import edu.poly.entity.Color;
import edu.poly.entity.ColorInventory;
import edu.poly.entity.ColorTop;
import edu.poly.service.ColorService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/colors")
public class ColorRestController {
	
	@Autowired
	ColorService cService;
	
	@GetMapping("top10")
	public List<Color> getTop10(){
		Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
		Page<Color> colors = cService.findAll(pageable);
		return colors.getContent();
	}
	
	@GetMapping()
	public List<Color> getAll(){
		List<Color> colors = cService.findAll(Sort.by("id").descending());
		return colors;
	}
	
	@PostMapping
	public Color create(@RequestBody Color color) {
		return cService.save(color) ;
	}
	
	@PutMapping("{id}")
	public Color update(@PathVariable("id") Integer id, @RequestBody Color color) {
		return cService.save(color);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		cService.deleteById(id);
	}
	
	@GetMapping("/top")
	public List<ColorTop> getColorTop() {
		List<ColorTop> list = cService.findColorTop();
		return list;
	}
	
	
	@GetMapping("/inventory")
	public List<ColorInventory> getBrandInventory() {
		List<ColorInventory> list = cService.findByColorInventory();
		return list;
	}
	
	
}
