package edu.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Brand;
@Repository
public interface BrandDAO extends JpaRepository<Brand, Integer>{
	
	
	@Query("SELECT b FROM Brand b where b.name like ?1")
	List<Brand> getListBrand(String valued);
}
