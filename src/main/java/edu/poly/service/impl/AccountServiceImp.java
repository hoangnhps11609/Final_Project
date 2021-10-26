package edu.poly.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.dao.AccountDAO;
import edu.poly.service.AccountService;
import edu.poly.entity.Account;
import edu.poly.entity.CountOrderOfAccount;
import edu.poly.entity.ReportAccountMonth;
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

	@Override
	public List<Account> getAccountByValud(String valued) {
		
		return adao.getListAccountByName(valued);
	}

	public List<Account> findAll(Sort sort) {
		return adao.findAll(sort);
	}

	@Override
	public List<CountOrderOfAccount> getCountOrder() {
		return adao.getCountOrder();
	}

	@Override
	public List<Account> findByDate(Date from, Date to) {
		return adao.findByDate(from, to);
	}

	@Override
	public List<Account> getLoyalCustomer() {
		return adao.getLoyalCustomer();
	}

	@Override
	public Long countCustomers() {
		return adao.countCustomers();
	}

	@Override
	public List<ReportAccountMonth> getAccount6Month() {
		return adao.getAccount6Month();
	}

	@Override
	public List<ReportAccountMonth> getAccountByTime(Date from, Date to) {
		return adao.getAccountByTime(from, to);
	}

	@Override
	public List<ReportAccountMonth> getAccountNoOrder() {
		return adao.getAccountNoOrder();
	}

	@Override
	public List<Double> getTopInAccount6Month() {
		return adao.getTopInAccount6Month();
	}

	@Override
	public List<Double> getTopInAccountNoOrder() {
		// TODO Auto-generated method stub
		return adao.getTopInAccountNoOrder();
	}

	@Override
	public List<Double> getTopInAccountByTime(Date from, Date to) {
		return adao.getTopInAccountByTime(from, to);
	}

	

}
