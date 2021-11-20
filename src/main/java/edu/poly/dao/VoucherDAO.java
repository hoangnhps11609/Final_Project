package edu.poly.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Account;
import edu.poly.entity.CountOrderOfAccount;
import edu.poly.entity.ReportAccountMonth;
import edu.poly.entity.Voucher;

@Repository
public interface VoucherDAO extends JpaRepository<Voucher, Integer>{

	@Query("Select a From Voucher a Where a.status = true")
	List<Voucher> getNewVoucher();
	
	@Query("Select a From Voucher a Where a.status = false")
	List<Voucher> getUsedVoucher();
	
	@Query("Select a From Voucher a Where a.name like ?1")
	List<Voucher> findVoucherByKeyword(String keyword);
	
	@Query("Select a From Voucher a Where a.name = ?1")
	List<Voucher> findVoucherByName(String name);

	@Query("Select a From Voucher a Where a.name = ?1 and a.status = 1")
	Voucher getVoucher(String name);
}
