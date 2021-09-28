package edu.poly.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Blog;
import edu.poly.entity.Product;
@Repository
public interface BlogDAO extends JpaRepository<Blog, Integer>{
	@Query
	("SELECT b FROM Blog b WHERE b.blogCategory.id=?1")
	List<Blog> findByCategoryId(Integer cid);
	
	Page<Blog> findByNameContaining(String name, Pageable pageable);

	@Query
	("SELECT b FROM Blog b WHERE b.blogCategory.id=?1")
	Page<Blog> findByCategoryId(Integer integer, Pageable pageable);
	
	@Query
	("SELECT b FROM Blog b WHERE b.name like ?1")
	Page<Blog> findByKeyword(String search, Pageable pageable);
}
