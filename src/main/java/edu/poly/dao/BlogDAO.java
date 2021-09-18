package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Blog;
@Repository
public interface BlogDAO extends JpaRepository<Blog, Integer>{

}
