package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Color;

@Repository
public interface ColorDAO extends JpaRepository<Color, Integer>{

	@Query
	("SELECT c FROM Color c WHERE c.id like ?1")
	Color findByColorId(Integer color);

}
