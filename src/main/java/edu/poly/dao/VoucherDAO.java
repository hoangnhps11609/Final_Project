package edu.poly.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Account;
import edu.poly.entity.CountOrderOfAccount;
import edu.poly.entity.ReportAccountMonth;
import edu.poly.entity.Voucher;

@Repository
public interface VoucherDAO extends JpaRepository<Voucher, Integer>{

	
}
