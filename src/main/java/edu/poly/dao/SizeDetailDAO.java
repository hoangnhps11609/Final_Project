package edu.poly.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.SizeDetail;

@Repository
public interface SizeDetailDAO extends JpaRepository<SizeDetail, Long>{

	@Query
	("SELECT s FROM SizeDetail s WHERE s.size.id=?1")
	Page<SizeDetail> findBySize(Integer sizepro, Pageable pageable);

}
