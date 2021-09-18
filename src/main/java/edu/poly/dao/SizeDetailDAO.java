package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.entity.SizeDetail;

@Repository
public interface SizeDetailDAO extends JpaRepository<SizeDetail, Integer>{

}
