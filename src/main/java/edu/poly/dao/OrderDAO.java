package edu.poly.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.poly.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Long>{

	@Query("SELECT o From Order o Where o.account.username=?1 Order By o.id DESC")
	Page<Order> findByUsername(String username, Pageable pageable);

	
	@Query("SELECT o From Order o Where o.createDate between ?1 and ?2 Order By o.id DESC")
	List<Order> findByDate(Date from, Date to);

}
