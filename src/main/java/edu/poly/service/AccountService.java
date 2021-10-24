package edu.poly.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;

import edu.poly.entity.Account;
import edu.poly.entity.CountOrderOfAccount;

public interface AccountService {
	Account findById(String username);

	List<Account> getAdministrators();

	List<Account> findAll();

	Account create(Account account);

	Account update(Account account);

	void delete(String id);
	
	List<Account> getAccountByValud(String valued);

	List<Account> findAll(Sort sort);

	List<CountOrderOfAccount> getCountOrder();

	List<Account> findByDate(Date from, Date to);

	List<Account> getLoyalCustomer();
}
