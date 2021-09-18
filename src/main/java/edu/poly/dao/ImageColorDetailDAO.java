package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.entity.ImageColorDetail;
import edu.poly.entity.SizeDetail;


public interface ImageColorDetailDAO extends JpaRepository<ImageColorDetail, Integer>{

}
