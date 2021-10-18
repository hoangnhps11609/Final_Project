package edu.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Category;
@Repository
public interface CategoryDAO extends JpaRepository<Category, String>{

	@Query
	("SELECT c FROM Category c WHERE c.id like ?1")
	Category findbyCateId(String categoryID);
	
	@Query
	("SELECT c FROM Category c WHERE c.name like ?1")
	List<Category> findbyName(String valued);



}
