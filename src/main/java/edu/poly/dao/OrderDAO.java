package edu.poly.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Order;
import edu.poly.entity.Product;
@Repository
public interface OrderDAO extends JpaRepository<Order, Long>{

	@Query("SELECT o From Order o Where o.account.username=?1 Order By o.id DESC")
	Page<Order> findByUsername(String username, Pageable pageable);

	
	@Query("SELECT o From Order o Where o.createDate between ?1 and ?2 Order By o.id DESC")
	List<Order> findByDate(Date from, Date to);
	
	@Query("SELECT o From Order o Where  o.account.username like ?1 or o.id like ?1")
	List<Order> FindById(String valued);

	@Query("SELECT o From Order o Where o.account.username=?1 and o.status = ?2")
	Page<Order> findByUsernameandStatus(String username, Integer sid, Pageable pageable);
	
	@Query
	("SELECT p FROM Order p WHERE p.id = ?1")
	Optional<Order> getChio(Long id);
	
	@Query
	("SELECT p FROM Order p WHERE p.status = 0")
	List<Order> findAllWaitingConfirm(Sort sort);
	
	@Query
	("SELECT p FROM Order p WHERE p.status = 1")
	List<Order> findAllConfirmed(Sort sort);
	
	@Query
	("SELECT p FROM Order p WHERE p.status = 2")
	List<Order> findAllShipping(Sort sort);
	
	@Query
	("SELECT p FROM Order p WHERE p.status = 3")
	List<Order> findAllComplete(Sort sort);
	
	@Query
	("SELECT p FROM Order p WHERE p.status = 4")
	List<Order> findAllCancelOrder(Sort sort);
}
