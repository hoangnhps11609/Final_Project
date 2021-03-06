package edu.poly.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Account;
import edu.poly.entity.Category;
import edu.poly.entity.CategoryInventory;
import edu.poly.entity.CategoryTop;
@Repository
public interface CategoryDAO extends JpaRepository<Category, String>{

	@Query
	("SELECT c FROM Category c WHERE c.id like ?1")
	Category findbyCateId(String categoryID);
	
	@Query
	("SELECT c FROM Category c WHERE c.name like ?1")
	List<Category> findbyName(String valued);

	@Query
	("SELECT new CategoryInventory(pd.product.category, sum(pd.quantity)) FROM ProductDetail pd group by pd.product.category order by sum(pd.quantity) desc")
	List<CategoryInventory> findCategoryInventory();

	@Query
	("SELECT new CategoryTop(od.productDetail.product.category, sum(od.quantity)) FROM OrderDetail od where od.order.status = 3 group by od.productDetail.product.category order by sum(od.quantity) desc")
	List<CategoryTop> findCategoryTop();
	
	@Query("Select DISTINCT a from Category a where a.id = ?1")
	Category findValidation(String validation);
	
	@Query
	("SELECT c FROM Category c WHERE c.id = ?1")
	Optional<Category> getChio(String id);
}