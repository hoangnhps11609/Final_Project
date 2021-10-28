package edu.poly.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.databind.JsonNode;

import edu.poly.entity.Order;
import edu.poly.entity.OrderDetail;
import edu.poly.entity.ReportItemDay;
import edu.poly.entity.ReportItemMonth;
import edu.poly.entity.ReportOrderDay;
import edu.poly.entity.ReportOrderMonth;
import edu.poly.entity.RevenueDay;
import edu.poly.entity.RevenueMonth;

public interface OrderService {

	Order create(JsonNode orderData);

	Order findById(Long id);

	Page<Order> findByUsername(String username, Pageable pageable);

	List<Order> findAll();

	Order create(Order order);

	Order update(Order order);

	void delete(Long id);

	List<OrderDetail> findByOrder(Long id);

	List<Order> findAll(Sort sort);

	List<Order> findByDate(Date fromFormat, Date toFormat);

	List<Order> findByUsername(String username);
	
	List<Order> findbyId(Long id);

	List<Order> findbyId(String id, Sort sort);
	
	Page<Order> findByUsernameandStatus(String username, Integer sid, Pageable pageable);

	<S extends Order> S save(S entity);
	
	Optional<Order> getChio(Long id);
	
	List<Order> findAllWaitingConfirm(Sort sort);
	
	List<Order> findAllConfirmed(Sort sort);
	
	List<Order> findAllShipping(Sort sort);
	
	List<Order> findAllComplete(Sort sort);
	
	List<Order> findAllCancelOrder(Sort sort);

	List<Order> findByUsernameandStatus(String username, Sort sort);

	Double getRevenue();

	Long getSales();

	Long countOrders();

	List<RevenueDay> getRevenue10Day(Sort sort);

	List<RevenueDay> getRevenueMonth(Sort sort);

	List<RevenueMonth> getRevenueYear();

	List<RevenueMonth> getRevenueByTime(Date from, Date to);

	List<ReportItemMonth> getItem6Month();

	List<ReportItemMonth> getItemByTime(Date from, Date to);

	List<ReportItemDay> getItemMonth(Sort sort);

	List<ReportOrderDay> getOrderMonth(Sort sort);

	List<ReportOrderMonth> getOrderByTime(Date from, Date to);

	List<ReportOrderMonth> getOrderCancelledYear();

	List<Double> getTopIn10Day();

	List<Double> getTopInRevenueMonth();

	List<Double> getTopInRevenueYear();

	List<Double> getTopInRevenueByTime(Date from, Date to);

	List<Long> getTopInItem6Month();

	List<Long> getTopInMonth();

	List<Double> getTopInItemByTime(Date from, Date to);

	List<Long> getTopInOrderMonth();

	List<Double> getTopInOrderByTime(Date from, Date to);

	List<Long> getTopInOrderCancelled();

	Long getTotalProByUsernameandStatus(String username);

	Double getTotalBillByUsernameandStatus(String username);

	List<Order> findNewOrderByUsername(String username);
}
