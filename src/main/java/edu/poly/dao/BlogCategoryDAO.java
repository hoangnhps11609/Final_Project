package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.entity.BlogCategory;

public interface BlogCategoryDAO extends JpaRepository<BlogCategory, Integer>{

}
