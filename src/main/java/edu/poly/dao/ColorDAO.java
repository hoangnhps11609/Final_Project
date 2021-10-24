package edu.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.BrandInventory;
import edu.poly.entity.CategoryTop;
import edu.poly.entity.Color;
import edu.poly.entity.ColorInventory;
import edu.poly.entity.ColorTop;

@Repository
public interface ColorDAO extends JpaRepository<Color, Integer>{

	@Query
	("SELECT c FROM Color c WHERE c.id like ?1")
	Color findByColorId(Integer color);
	
	@Query
	("SELECT new ColorTop(od.productDetail.color, sum(od.quantity)) FROM OrderDetail od where od.order.status = 3 group by od.productDetail.color")
	List<ColorTop> findColorTop();
	
	@Query
	("SELECT new ColorInventory(pd.color, sum(pd.quantity)) FROM ProductDetail pd group by pd.color")
	List<ColorInventory> findColorInventory();
	

	
}
