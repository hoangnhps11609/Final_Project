package edu.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Comment;
@Repository
public interface CommentDAO extends JpaRepository<Comment, Integer>{

	
	@Query
	("Select c from Comment c where c.product.id = ?1")
	List<Comment> findByProductId(Integer id);

}
