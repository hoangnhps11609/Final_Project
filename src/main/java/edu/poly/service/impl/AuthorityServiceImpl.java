package edu.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.poly.authority.service.*;
import edu.poly.dao.AccountDAO;
import edu.poly.dao.AuthorityDAO;
import edu.poly.entity.Account;
import edu.poly.entity.Authority;
import edu.poly.entity.Category;
@Service
public class AuthorityServiceImpl implements AuthorityService{

	@Autowired
	AuthorityDAO dao;
	
	@Autowired
	AccountDAO acdao;

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = acdao.getAdministrators();
		return dao.authoritiesOf(accounts);
	}

	@Override
	public List<Authority> findAll() {
		return dao.findAll();
	}

	@Override
	public Authority create(Authority auth) {
		return dao.save(auth);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}
}
