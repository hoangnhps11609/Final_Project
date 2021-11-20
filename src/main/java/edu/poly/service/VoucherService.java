package edu.poly.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import edu.poly.entity.Account;
import edu.poly.entity.CountOrderOfAccount;
import edu.poly.entity.ReportAccountMonth;
import edu.poly.entity.Voucher;

public interface VoucherService {
	List<Voucher> findAll(Sort sort);
	
	List<Voucher> getNewVoucher();
	
	List<Voucher> getUsedVoucher();
	
	List<Voucher> findVoucherByKeyword(String keyword);
	
	Voucher create(Voucher account);
	
	void delete(Integer id);
	
	Voucher update(Voucher voucher);
	
	List<Voucher> findVoucherByName(String name);
	
	Voucher getVoucher(String name);





}
