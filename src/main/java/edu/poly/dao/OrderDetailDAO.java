package edu.poly.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.OrderDetail;
@Repository
public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {

	
	@Query("SELECT od FROM OrderDetail od WHERE od.order.id=?1")
	Page<OrderDetail> findByOrder(Long id, Pageable pageable);

	@Query("SELECT od FROM OrderDetail od WHERE od.order.id=?1")
	List<OrderDetail> findByOrder(Long id);

}
