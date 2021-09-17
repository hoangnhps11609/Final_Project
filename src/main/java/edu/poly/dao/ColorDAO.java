package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.entity.Color;


public interface ColorDAO extends JpaRepository<Color, Integer>{

}
