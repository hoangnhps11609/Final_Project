package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.entity.Brand;

public interface BrandDAO extends JpaRepository<Brand, Integer>{

}
