package edu.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Size;
@Repository
public interface SizeDAO extends JpaRepository<Size, Integer>{

	@Query
	("Select s from Size s where s.description like ?1")
	List<Size> findSizeByCate(String string);

	@Query
	("Select s from Size s where s.id = ?1")
	Size findBySizeId(Integer sizepro);

}
