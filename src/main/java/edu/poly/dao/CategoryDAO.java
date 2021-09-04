package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, String>{

}
