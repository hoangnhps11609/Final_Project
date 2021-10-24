package edu.poly.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Account;
import edu.poly.entity.CountOrderOfAccount;

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
