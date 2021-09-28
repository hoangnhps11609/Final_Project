package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Color;

@Repository
public interface ColorDAO extends JpaRepository<Color, Integer>{

}
