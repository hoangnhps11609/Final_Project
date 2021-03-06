package edu.poly.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Comment;
import edu.poly.entity.Product;
import edu.poly.entity.RateAVG;
@Repository
public interface CommentDAO extends JpaRepository<Comment, Integer>{

	
	@Query
	("Select c from Comment c where c.product.id = ?1")
	Page<Comment> findByProductId(Integer id, Pageable pageable);

	@Query
	("Select new RateAVG(c.product.id, avg(c.rate)) from Comment c where c.product.id = ?1 group by c.product.id")
	RateAVG rateAVG(Integer id);
	
	
	@Query
	("Select c from Comment c where c.id = ?1")
	Optional<Comment> getChio(Integer id);
	
	@Query
	("Select c from Comment c where c.status = true")
	List<Comment> XemTatCaCommentDaDoc();
	
	@Query
	("Select c from Comment c where c.status = false")
	List<Comment> XemTatCaCommentChuaDoc();
	
	@Query
	("Select c from Comment c where c.account.fullname like ?1 or c.product.name like ?1")
	List<Comment> XemTatCaCommentThuocFullnameHoacProductName(String valued);
}
