package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Category;
@Repository
public interface CategoryDAO extends JpaRepository<Category, String>{

}
