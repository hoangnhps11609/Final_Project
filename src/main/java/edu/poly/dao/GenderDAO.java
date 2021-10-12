package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Gender;
@Repository
public interface GenderDAO extends JpaRepository<Gender, Integer>{

	@Query
	("SELECT g FROM Gender g WHERE g.id = ?1")
	Gender findbyGenderId(Integer gender);

}
