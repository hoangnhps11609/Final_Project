package edu.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Brand;
import edu.poly.entity.BrandInventory;
import edu.poly.entity.BrandTop;
import edu.poly.entity.CategoryInventory;
import edu.poly.entity.CategoryTop;
@Repository
public interface BrandDAO extends JpaRepository<Brand, Integer>{
	
	
	@Query("SELECT b FROM Brand b where b.name like ?1")
	List<Brand> getListBrand(String valued);

	@Query("SELECT b FROM Brand b where b.id = ?1")
	Brand findbyBrandId(Integer brand);
	
	@Query
	("SELECT new BrandTop(od.productDetail.product.brand, sum(od.quantity)) FROM OrderDetail od where od.order.status = 3 group by od.productDetail.product.brand")
	List<BrandTop> findBrandTop();
	
	@Query
	("SELECT new BrandInventory(pd.product.brand, sum(pd.quantity)) FROM ProductDetail pd group by pd.product.brand")
	List<BrandInventory> findBrandInventory();

}
