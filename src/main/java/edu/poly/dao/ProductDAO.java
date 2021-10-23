package edu.poly.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Product;
import edu.poly.entity.ProductByColor;
import edu.poly.entity.ProductDetail;
import edu.poly.entity.Report;
@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
	
	@Query
	("SELECT p FROM Product p WHERE p.category.id=?1 and p.available = 'true'")
	List<Product> findByCategoryId(String cid, Sort sort);

	Page<Product> findByNameContaining(String name, Pageable pageable);

	@Query
	("SELECT p FROM Product p WHERE p.category.id=?1 and p.available = 'true'")
	Page<Product> findByCategoryId(String string, Pageable pageable);

	
	@Query
	("SELECT p FROM Product p WHERE p.price between ?1 and ?2 and p.available = 'true'")
	Page<Product> findByPriceContaining(Double min, Double max, Pageable pageable);

	@Query
	("SELECT p FROM Product p WHERE p.category.id=?1 and p.price between ?2 and ?3 and p.available = 'true'")
	Page<Product> findByCategoryIdAndPrice(String categoryID, Double min, Double max, Pageable pageable);

	
	@Query
	("SELECT p FROM Product p WHERE p.name like ?1 and p.available = 'true'")
	Page<Product> findByKeyword(String search, Pageable pageable);

	
	
	@Query
	("SELECT p FROM Product p WHERE p.category.id=?1 and p.gender.id=?2 and p.available = 'true'")
	List<Product> findByCategoryIdandGender(String categoryid, int gender);
	

	@Query("SELECT new Report(o.category, sum(o.price), count(o))   FROM Product o  GROUP BY o.category" + " ORDER BY sum(o.price) DESC")
	List<Report> getInventoryByCategory();

	
	@Query
	("SELECT p FROM Product p WHERE p.brand.id=?1 and p.available = 'true'")
	Page<Product> findByBrandId(Integer brand, Pageable pageable);

	@Query
	("SELECT p FROM Product p WHERE p.gender.id=?1 and p.available = 'true'")
	Page<Product> findByGenderId(Integer gender, Pageable pageable);

	@Query
	("SELECT p FROM Product p WHERE p.name like ?1 or p.id like ?1 and p.available = 'true'")
	List<Product> findbyName(String valued);
	
	@Query
	("SELECT p FROM Product p WHERE p.available = 'true'")
	Page<Product> findAllTrue(Pageable pageable);
	

	@Query
	("SELECT p FROM Product p WHERE p.category.id like ?1 and p.brand.name like ?2 and p.gender.name like ?3 and p.price between ?4 and ?5")
	Page<Product> filterProduct(String cateid, String brandname, String gendername, double min, double max, Pageable pageable);
	 
	@Query
	("SELECT p FROM Product p WHERE p.id = ?1")
	Optional<Product> getChio(Integer id);
	
	@Query
	("SELECT p FROM Product p WHERE p.id = ?1")
	List<Product> ThinhWaMetMoi(Integer id);


	@Query
	("SELECT p FROM Product p WHERE p.brand.id=?1")
	List<Product> findByBrandId(Integer brandid);

	
	@Query
	("Select sum(pd.quantity) from ProductDetail pd where pd.product.category.id = ?1 group by pd.product.category")
	Long getCount(String cateid);

	@Query
	("Select sum(pd.quantity) from ProductDetail pd where pd.product.id = ?1 group by pd.product")
	Long getCountProDetail(Integer id);

	@Query
	("SELECT new ProductByColor(p.product, count(p.product)) FROM ProductDetail p WHERE p.color.id=?1 group by p.product")
	List<ProductByColor> getProInColor(Integer id);
	



}
