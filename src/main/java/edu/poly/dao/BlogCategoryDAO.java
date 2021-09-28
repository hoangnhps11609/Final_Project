package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.entity.BlogCategory;
@Repository
public interface BlogCategoryDAO extends JpaRepository<BlogCategory, Integer>{

}
