package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.entity.ImageColorDetail;

@Repository
public interface ImageColorDetailDAO extends JpaRepository<ImageColorDetail, Integer>{

}
