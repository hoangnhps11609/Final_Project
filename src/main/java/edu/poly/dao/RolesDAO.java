package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Role;

@Repository
public interface RolesDAO extends JpaRepository<Role, String> {

}
