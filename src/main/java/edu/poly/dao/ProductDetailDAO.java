package edu.poly.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.ColorPro;
import edu.poly.entity.Product;
import edu.poly.entity.ProductDetail;
import edu.poly.entity.SizePro;
@Repository
public interface ProductDetailDAO extends JpaRepository<ProductDetail, Long> {

	
	@Query
	("SELECT p FROM ProductDetail p WHERE p.color.id=?1")
	Page<ProductDetail> findByColor(Integer color, Pageable pageable);

	
	@Query
	("SELECT p FROM ProductDetail p WHERE p.size.id=?1")
	Page<ProductDetail> findBySize(Integer sizepro, Pageable pageable);

	@Query
	("SELECT p FROM ProductDetail p WHERE p.product.id=?1")
	Product findByProductId(Integer id);
	
	@Query
	("SELECT new ColorPro(p.color.id, p.color.name) FROM ProductDetail p WHERE p.product.id=?1  group by p.color.id, p.color.name")
	List<ColorPro> getColorByProduct(Integer id);
	
	@Query
	("SELECT new SizePro(p.size.id, p.size.name, p.size.description) FROM ProductDetail p WHERE p.product.id=?1  group by p.size.id, p.size.name, p.size.description")
	List<SizePro> getSizeByProduct(Integer id);
}
