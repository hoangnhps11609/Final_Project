package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Gender;
@Repository
public interface GenderDAO extends JpaRepository<Gender, Integer>{

}
