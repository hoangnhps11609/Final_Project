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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.entity.Account;
import edu.poly.entity.Comment;
import edu.poly.entity.Product;
import edu.poly.entity.Size;
import edu.poly.service.AccountService;
import edu.poly.service.CommentService;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/comments")
public class CommentRestController {
	@Autowired
	CommentService commentService;
	
	@Autowired
	AccountService accountService;

	@Autowired
	HttpServletRequest request;
	
//	
//	@GetMapping
//	public List<Account> getAccounts(@RequestParam("admin")Optional<Boolean> admin){
//		if (admin.orElse(false)) {
//			return accService.getAdministrators();
//		}
//		return accService.findAll();
//	}
//	
//
//	
//	@GetMapping("get/{username}")
//	public Account getOne(@PathVariable("username") String username) {
//		return accService.findById(username);
//	}
	
	@GetMapping
	public List<Comment> getAll(){
		return commentService.findAll();
	}
	
	@GetMapping("chuadoc")
	public List<Comment> getAlCommentChuaDoc(){
		return commentService.XemTatCaCommentChuaDoc();
	}
	
	@GetMapping("dadoc")
	public List<Comment> getAllCommentDaDoc(){
		return commentService.XemTatCaCommentDaDoc();
	}
	
	@GetMapping("{valued}")
	public List<Comment> getListAccountByValued(@PathVariable("valued") String valued){
	
		return commentService.XemTatCaCommentThuocFullnameHoacProductName("%"+valued+"%");	
	}
		
	
	@PostMapping
	public Comment create(@RequestBody Comment comment) {
		String username = request.getRemoteUser();
		Account account = accountService.findById(username);
		
		comment.setAccount(account);
		return commentService.create(comment);
	}
	
	@PutMapping("get/{id}")
	public Comment update2(@PathVariable("id") Integer id, @RequestBody Comment comment) {
		Optional<Comment> a = commentService.getChio(id);
		comment.setId(id);
		comment.setAccount(a.get().getAccount());
		comment.setContent(a.get().getContent());
		comment.setCreateDate(a.get().getCreateDate());
		comment.setPhoto(a.get().getPhoto());
		comment.setProduct(a.get().getProduct());
		comment.setRate(a.get().getRate());
		comment.setStatus(true);
		return commentService.save(comment);
	}
	
//	
//	@PutMapping("{id}")
//	public Account update(@PathVariable("id") String id, @RequestBody Account account) {
//		return accService.update(account);
//	}
//	
//	@DeleteMapping("{id}")
//	public void delete(@PathVariable("id") String id) {
//		accService.delete(id);
//	}
//	
//	@GetMapping("{valued}")
//	public List<Account> getListAccountByValued(@PathVariable("valued") String valued){
//		return accService.getAccountByValud(valued+"%");
//		
//	}
}
