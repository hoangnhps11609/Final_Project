package edu.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account, String>{

	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id in ('DIRE','STAF')")
	List<Account> getAdministrators();

	Account findByUsername(String username);

}
