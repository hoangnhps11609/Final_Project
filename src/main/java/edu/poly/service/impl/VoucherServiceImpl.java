package edu.poly.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Optional;

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

	@Override
	public List<Voucher> getNewVoucher() {
		// TODO Auto-generated method stub
		return vcDAO.getNewVoucher();
	}

	@Override
	public List<Voucher> getUsedVoucher() {
		// TODO Auto-generated method stub
		return vcDAO.getUsedVoucher();
	}

	@Override
	public List<Voucher> findVoucherByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return vcDAO.findVoucherByKeyword(keyword);
	}

	@Override
	public Voucher create(Voucher voucher) {
		// TODO Auto-generated method stub
		return vcDAO.save(voucher);
	}

	@Override
	public void delete(Integer id) {
		vcDAO.deleteById(id);
	}

	@Override
	public Voucher update(Voucher voucher) {
		// TODO Auto-generated method stub
		return vcDAO.save(voucher);
	}

	@Override
	public List<Voucher> findVoucherByName(String name) {
		// TODO Auto-generated method stub
		return vcDAO.findVoucherByName(name);
	}

	@Override
	public Voucher getVoucher(String name) {
		return vcDAO.getVoucher(name);
	}

	

	

}
