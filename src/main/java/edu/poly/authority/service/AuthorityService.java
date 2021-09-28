package edu.poly.authority.service;

import java.util.List;

import edu.poly.entity.Authority;
import edu.poly.entity.Category;

public interface AuthorityService {

	List<Authority> findAuthoritiesOfAdministrators();

	List<Authority> findAll();

	Authority create(Authority auth);

	void delete(Integer id);

}
