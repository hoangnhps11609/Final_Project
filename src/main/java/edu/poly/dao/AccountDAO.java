package edu.poly.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Account;
import edu.poly.entity.CountOrderOfAccount;
import edu.poly.entity.ReportAccountMonth;

@Repository
public interface AccountDAO extends JpaRepository<Account, String>{

	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id in ('DIRE','STAF')")
	List<Account> getAdministrators();

	Account findByUsername(String username);
	
	@Query("SELECT a FROM Account a where a.username like ?1 or a.fullname like ?1 or a.email like ?1")
	List<Account> getListAccountByName(String valued);

	
	@Query("SELECT new CountOrderOfAccount(o.account, count(o.account.username)) FROM Order o where o.status = 3  group by o.account.username having count(o.account.username) >= 5 order by count(o.account.username) desc")
	List<CountOrderOfAccount> getCountOrder();

	@Query("SELECT a FROM Account a where a.createDate between ?1 and ?2")
	List<Account> findByDate(Date from, Date to);

	

	@Query("SELECT a FROM Account a where DATEDIFF(day, a.createDate, GETDATE()) >= 20")
	List<Account> getLoyalCustomer();


	@Query("Select count(a) from Account a")
	Long countCustomers();

	@Query("Select new ReportAccountMonth(Month(a.createDate), Year(a.createDate), count(a)) from Account a where a.createDate between DATEADD(MONTH, -6, GETDATE()) and GETDATE() group by MONTH(a.createDate), YEAR(a.createDate)  order by month(a.createDate) desc")
	List<ReportAccountMonth> getAccount6Month();

	@Query("Select new ReportAccountMonth(Month(a.createDate), Year(a.createDate), count(a)) from Account a where a.createDate between ?1 and ?2 group by MONTH(a.createDate), YEAR(a.createDate)  order by month(a.createDate) desc")
	List<ReportAccountMonth> getAccountByTime(Date from, Date to);

	@Query("select new ReportAccountMonth(Month(a.createDate), Year(a.createDate), count(a))\r\n"
			+ "from Account a\r\n"
			+ "where a.username not in (select distinct o.account.username from Order o	where o.status=3)\r\n"
			+ "	and a.createDate between DATEADD(MONTH, -6, GETDATE()) and GETDATE()\r\n"
			+ "group by MONTH(a.createDate), YEAR(a.createDate) order by month(a.createDate) desc")
	List<ReportAccountMonth> getAccountNoOrder();

	
	
	@Query("Select count(a) from Account a where a.createDate between DATEADD(MONTH, -6, GETDATE()) and GETDATE() group by MONTH(a.createDate), YEAR(a.createDate)  order by count(a) desc")
	List<Double> getTopInAccount6Month();

	
	@Query("select count(a)\r\n"
			+ "from Account a\r\n"
			+ "where a.username not in (select distinct o.account.username from Order o	where o.status=3)\r\n"
			+ "	and a.createDate between DATEADD(MONTH, -6, GETDATE()) and GETDATE()\r\n"
			+ "group by MONTH(a.createDate), YEAR(a.createDate) order by count(a) desc")
	List<Double> getTopInAccountNoOrder();

	@Query("Select count(a) from Account a where a.createDate between ?1 and ?2 group by MONTH(a.createDate), YEAR(a.createDate)  order by count(a) desc")
	List<Double> getTopInAccountByTime(Date from, Date to);

	
	@Query("Select a from Account a where a.username = ?1 or a.phone = ?1 or a.email = ?1")
	List<Account> findValidation(String validation);


	@Query("Select a from Account a where a.birthday = ?1")
	List<Account> listHPBD(Date date);
	
	
//	@Query(value="select acc.*, o.sodonhang, o.Tongtien\r\n"
//			+ "from Accounts acc join (select o.Username, count(o.username) as 'sodonhang', sum(o.sum) as 'Tongtien'\r\n"
//			+ "							from (select sum(Quantity*Price) as sum, OrderId, o.Username\r\n"
//			+ "										from OrderDetails od join Orders o on od.OrderId = o.Id\r\n"
//			+ "										where o.Status =3\r\n"
//			+ "										group by OrderId, Username) o\r\n"
//			+ "							group by o.Username) o \r\n"
//			+ "	on acc.Username = o.Username", nativeQuery = true)
//	List<Object[]> getSumOrder = 
	
}
