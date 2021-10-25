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
import edu.poly.entity.ReportItemDay;
import edu.poly.entity.ReportItemMonth;
import edu.poly.entity.ReportOrderDay;
import edu.poly.entity.ReportOrderMonth;
import edu.poly.entity.RevenueDay;
import edu.poly.entity.RevenueMonth;
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


	@Query("SELECT o From Order o Where o.account.username=?1 and o.status = 3")
	List<Order> findByUsernameandStatus(String username, Sort sort);

	@Query("SELECT sum(o.total) From Order o Where o.status = 3")
	Double getRevenue();


	@Query("SELECT sum(o.quantity) From Order o Where o.status = 3")
	Long getSales();


	@Query("SELECT count(o) From Order o Where o.status = 3")
	Long countOrders();


	@Query("Select New RevenueDay(o.createDate, sum(o.total)) from Order o where o.status = 3 and o.createDate between DATEADD(DAY, -10, GETDATE()) and GETDATE() group by o.createDate")
	List<RevenueDay> getRevenue10Day(Sort sort);


	@Query("Select New RevenueDay(o.createDate, sum(o.total)) from Order o where o.status = 3 and MONTH(CreateDate) = MONTH(GETDATE()) and YEAR(CreateDate) = YEAR(GETDATE()) group by o.createDate")
	List<RevenueDay> getRevenueMonth(Sort sort);

	@Query("Select New RevenueMonth(Month(o.createDate), Year(o.createDate), sum(o.total)) from Order o where o.status = 3 and YEAR(CreateDate) = YEAR(GETDATE()) group by Month(o.createDate), Year(o.createDate) order by month(o.createDate) desc")
	List<RevenueMonth> getRevenueYear();


	@Query("Select New RevenueMonth(Month(o.createDate), Year(o.createDate), sum(o.total)) from Order o where o.status = 3 and o.createDate between ?1 and ?2 group by Month(o.createDate), Year(o.createDate) order by month(o.createDate) desc")
	List<RevenueMonth> getRevenueByTime(Date from, Date to);

	@Query("Select New ReportItemMonth(Month(o.createDate), Year(o.createDate), sum(o.quantity)) from Order o where o.status = 3 and o.createDate between DATEADD(Month, -6, GETDATE()) and GETDATE() group by Month(o.createDate), Year(o.createDate)  order by month(o.createDate) desc")
	List<ReportItemMonth> getItem6Month();

	@Query("Select New ReportItemMonth(Month(o.createDate), Year(o.createDate), sum(o.quantity)) from Order o where o.status = 3 and o.createDate between ?1 and ?2 group by Month(o.createDate), Year(o.createDate)  order by month(o.createDate) desc")
	List<ReportItemMonth> getItemByTime(Date from, Date to);

	@Query("Select New ReportItemDay(o.createDate, sum(o.quantity)) from Order o where o.status = 3 and MONTH(CreateDate) = MONTH(GETDATE()) and YEAR(CreateDate) = YEAR(GETDATE()) group by o.createDate")
	List<ReportItemDay> getItemMonth(Sort sort);

	@Query("Select New ReportOrderDay(o.createDate, count(o)) from Order o where o.status = 3 and MONTH(CreateDate) = MONTH(GETDATE()) and YEAR(CreateDate) = YEAR(GETDATE()) group by o.createDate")
	List<ReportOrderDay> getOrderMonth(Sort sort);


	@Query("Select New ReportOrderMonth(Month(o.createDate), Year(o.createDate), count(o)) from Order o where o.status = 3 and o.createDate between ?1 and ?2 group by Month(o.createDate), Year(o.createDate) order by month(o.createDate) desc")
	List<ReportOrderMonth> getOrderByTime(Date from, Date to);

	@Query("Select New ReportOrderMonth(Month(o.createDate), Year(o.createDate), count(o)) from Order o where o.status = 4 and YEAR(CreateDate) = YEAR(GETDATE()) group by Month(o.createDate), Year(o.createDate) order by month(o.createDate) desc")
	List<ReportOrderMonth> getOrderCancelledYear();
	
	

}
