package edu.poly.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Product;
import edu.poly.entity.Report;
@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
	
	@Query
	("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(String cid);

	Page<Product> findByNameContaining(String name, Pageable pageable);

	@Query
	("SELECT p FROM Product p WHERE p.category.id=?1")
	Page<Product> findByCategoryId(String string, Pageable pageable);

	
	@Query
	("SELECT p FROM Product p WHERE p.price between ?1 and ?2")
	Page<Product> findByPriceContaining(Double min, Double max, Pageable pageable);

	@Query
	("SELECT p FROM Product p WHERE p.category.id=?1 and p.price between ?2 and ?3")
	Page<Product> findByCategoryIdAndPrice(String categoryID, Double min, Double max, Pageable pageable);

	
	@Query
	("SELECT p FROM Product p WHERE p.name like ?1")
	Page<Product> findByKeyword(String search, Pageable pageable);

	
	
	@Query
	("SELECT p FROM Product p WHERE p.category.id=?1 and p.gender.id=?2")
	List<Product> findByCategoryIdandGender(String categoryid, int gender);
	

	@Query("SELECT new Report(o.category, sum(o.price), count(o))   FROM Product o  GROUP BY o.category" + " ORDER BY sum(o.price) DESC")
	List<Report> getInventoryByCategory();

	
	@Query
	("SELECT p FROM Product p WHERE p.brand.id=?1")
	Page<Product> findByBrandId(Integer brand, Pageable pageable);

	@Query
	("SELECT p FROM Product p WHERE p.gender.id=?1")
	Page<Product> findByGenderId(Integer gender, Pageable pageable);

	

}
