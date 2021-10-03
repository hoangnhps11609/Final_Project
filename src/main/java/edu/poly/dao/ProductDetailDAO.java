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
	("SELECT new ColorPro(p.color.id, p.color.name) FROM ProductDetail p WHERE p.product.id=?1 and p.size.id=?2  group by p.color.id, p.color.name")
	List<ColorPro> getColorByProduct(Integer id, Integer sizepro);
	
	@Query
	("SELECT new SizePro(p.size.id, p.size.name, p.size.description) FROM ProductDetail p WHERE p.product.id=?1  group by p.size.id, p.size.name, p.size.description")
	List<SizePro> getSizeByProduct(Integer id);


	@Query
	("SELECT new ColorPro(p.color.id, p.color.name) FROM ProductDetail p WHERE p.product.id=?1  group by p.color.id, p.color.name")
	List<ColorPro> getColorByProduct(Integer id);

	@Query
	("SELECT new SizePro(p.size.id, p.size.name, p.size.description) FROM ProductDetail p WHERE p.product.id=?1 and p.color.id=?2  group by p.size.id, p.size.name, p.size.description")
	List<SizePro> getSizeByProduct(Integer id, Integer colorpro);

	@Query
	("SELECT p FROM ProductDetail p WHERE p.product.id=?1 and p.size.id=?2")
	List<ProductDetail> findByProductIDandSizeID(Integer id, Integer sizepro);

	@Query
	("SELECT p FROM ProductDetail p WHERE p.product.category.id like ?1 and p.product.brand.id like ?2 and p.size.id like ?3 and p.product.gender.id like ?4 and p.color.id like ?5 and p.product.price between ?6 and ?7")
	Page<ProductDetail> filterAllProduct(String cateid, int i, int j, int k, int l,
			double m, double n, Pageable pageable);
}
