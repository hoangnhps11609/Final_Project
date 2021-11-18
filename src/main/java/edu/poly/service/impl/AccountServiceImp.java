package edu.poly.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.microsoft.sqlserver.jdbc.StringUtils;

import edu.poly.dao.AccountDAO;
import edu.poly.service.AccountService;
import edu.poly.entity.Account;
import edu.poly.entity.CountOrderOfAccount;
import edu.poly.entity.ReportAccountMonth;

@Service
public class AccountServiceImp implements AccountService{

	@Autowired
	AccountDAO adao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
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

	@Override
	public Account findValidation(String validation) {
		return adao.findValidation(validation);
	}

	@Override
	public <S extends Account> S save(S account) {
		Optional<Account> optExist = findByUsername(account.getUsername());
		if (optExist.isPresent()) {
			if (StringUtils.isEmpty(account.getPassword())) {
				account.setPassword(optExist.get().getPassword());
			} else {
				account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
			}
		}else {
			account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));	
		}
		return adao.save(account);
	}
	
	@Override
	public Account login(String username, String password) {
		Optional<Account> optExist = findByUsername(username);
		if (optExist.isPresent() && bCryptPasswordEncoder.matches(password, optExist.get().getPassword())) {
			optExist.get().setPassword("");
			return optExist.get();
		}
		return null;
	}

	
	@Override
	public Optional<Account> findByUsername(String id) {
		return adao.findById(id);
	}
}
