package edu.poly.rest.controller;

import java.util.List;

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

import edu.poly.entity.Color;
import edu.poly.entity.ColorInventory;
import edu.poly.entity.ColorTop;
import edu.poly.entity.Size;
import edu.poly.entity.SizeInventory;
import edu.poly.entity.SizeTop;
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
	
	@PostMapping
	public Size create(@RequestBody Size size) {
		return cService.save(size) ;
	}
	
	@PutMapping("{id}")
	public Size update(@PathVariable("id") Integer id, @RequestBody Size size) {
		return cService.save(size);
	}
	
	@GetMapping("{valued}")
	public List<Size> TimKiemTheoNameCuaSize(@PathVariable ("valued")String valued){
		return cService.TimTheoNameCuaSize("%"+valued+"%");
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		cService.deleteById(id);
	}
	
	@GetMapping("/top")
	public List<SizeTop> getColorTop() {
		List<SizeTop> list = cService.findSizeTop();
		return list;
	}
	
	@GetMapping("get/{valued}")
	public List<Size> TimTatCaSanPhamThuocSize(@PathVariable ("valued")String valued){
		return cService.TimKiemTatCaSanPhamThuocSize(valued);
	}
	
	@GetMapping("/inventory")
	public List<SizeInventory> getSizeInventory() {
		List<SizeInventory> list = cService.findSizeInventory();
		return list;
	}
}
