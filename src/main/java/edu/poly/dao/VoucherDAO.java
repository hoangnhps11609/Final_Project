package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.entity.Voucher;

public interface VoucherDAO extends JpaRepository<Voucher, Integer>{

}
