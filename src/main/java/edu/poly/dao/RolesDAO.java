package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.entity.Role;


public interface RolesDAO extends JpaRepository<Role, String> {

}
