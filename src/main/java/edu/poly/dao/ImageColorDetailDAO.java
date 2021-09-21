package edu.poly.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.ImageColorDetail;

@Repository
public interface ImageColorDetailDAO extends JpaRepository<ImageColorDetail, Long>{

	
	@Query
	("SELECT i FROM ImageColorDetail i WHERE i.color.id=?1")
	Page<ImageColorDetail> findByColor(Integer color, Pageable pageable);

}
