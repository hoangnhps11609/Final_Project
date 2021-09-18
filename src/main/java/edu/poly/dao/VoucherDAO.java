package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Voucher;
@Repository
public interface VoucherDAO extends JpaRepository<Voucher, Integer>{

}
