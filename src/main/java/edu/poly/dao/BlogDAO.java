package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.entity.Blog;

public interface BlogDAO extends JpaRepository<Blog, Integer>{

}
