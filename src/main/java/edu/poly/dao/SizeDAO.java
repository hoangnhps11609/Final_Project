package edu.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.ColorInventory;
import edu.poly.entity.ColorTop;
import edu.poly.entity.Size;
import edu.poly.entity.SizeInventory;
import edu.poly.entity.SizeTop;
@Repository
public interface SizeDAO extends JpaRepository<Size, Integer>{

	@Query
	("Select s from Size s where s.description like ?1")
	List<Size> findSizeByCate(String string);

	@Query
	("Select s from Size s where s.id = ?1")
	Size findBySizeId(Integer sizepro);
	
	@Query
	("Select s from Size s where s.name like ?1")
	List<Size> TimKiemBoiNameCuaSize(String size);
	
	@Query
	("Select s from Size s where s.name = ?1")
	List<Size> TimKiemTatCaSanPhanThuocSize (String size);
	
	@Query
	("SELECT new SizeTop(od.productDetail.size, sum(od.quantity)) FROM OrderDetail od where od.order.status = 3 group by od.productDetail.size")
	List<SizeTop> findSizeTop();
	
	@Query
	("SELECT new SizeInventory(pd.size, sum(pd.quantity)) FROM ProductDetail pd group by pd.size")
	List<SizeInventory> findSizeInventory();
	


}
