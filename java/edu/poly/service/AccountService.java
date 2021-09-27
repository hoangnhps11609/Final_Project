package edu.poly.service;

import java.util.List;

import edu.poly.entity.Account;

public interface AccountService {
	Account findById(String username);

	List<Account> getAdministrators();

	List<Account> findAll();

	Account create(Account account);

	Account update(Account account);

	void delete(String id);
	
	List<Account> getAccountByValud(String valued);
}
