package edu.poly.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.poly.dao.AccountDAO;
import edu.poly.service.AccountService;
import edu.poly.entity.Account;
@Service
public class AccountServiceImp implements AccountService{

	@Autowired
	AccountDAO adao;
	
	@Override
	public Account findById(String username){
		return adao.findById(username).get();
	}

	@Override
	public List<Account> getAdministrators() {
		return adao.getAdministrators();
	}

	@Override
	public List<Account> findAll() {
		return adao.findAll();
	}

	@Override
	public Account create(Account account) {
		return adao.save(account);
	}

	@Override
	public Account update(Account account) {
		return adao.save(account);
	}

	@Override
	public void delete(String id) {
		adao.deleteById(id);	
	}

}
