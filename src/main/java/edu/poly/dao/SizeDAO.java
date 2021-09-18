package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Size;
@Repository
public interface SizeDAO extends JpaRepository<Size, Integer>{

}
