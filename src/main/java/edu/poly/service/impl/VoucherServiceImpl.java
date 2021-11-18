package edu.poly.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.dao.AccountDAO;
import edu.poly.dao.SizeDAO;
import edu.poly.dao.VoucherDAO;
import edu.poly.service.AccountService;
import edu.poly.service.VoucherService;
import edu.poly.entity.Account;
import edu.poly.entity.CountOrderOfAccount;
import edu.poly.entity.ReportAccountMonth;
import edu.poly.entity.Voucher;
@Service
public class VoucherServiceImpl implements VoucherService{
	
	@Autowired
	VoucherDAO vcDAO;
	
	@Override
	public List<Voucher> findAll() {
	
		return vcDAO.findAll();
	}

	

	

}
