package edu.poly.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import edu.poly.entity.Account;
import edu.poly.entity.CountOrderOfAccount;
import edu.poly.entity.ReportAccountMonth;

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

	Long countCustomers();

	List<ReportAccountMonth> getAccount6Month();

	List<ReportAccountMonth> getAccountByTime(Date from, Date to);

	List<ReportAccountMonth> getAccountNoOrder();

	List<Double> getTopInAccount6Month();

	List<Double> getTopInAccountNoOrder();

	List<Double> getTopInAccountByTime(Date from, Date to);

	List<Account> findValidation(String validation);

	<S extends Account> S save(S account);
	
	Optional<Account> findByUsername(String id);

	Account login(String username, String password);

	List<Account> findListByUsername(String string);

}
