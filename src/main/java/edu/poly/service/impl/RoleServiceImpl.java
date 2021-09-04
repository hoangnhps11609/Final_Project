package edu.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.poly.dao.RolesDAO;
import edu.poly.service.RoleService;
import edu.poly.entity.Role;
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RolesDAO cdao;
	
	@Override
	public List<Role> findAll() {
		return cdao.findAll()	;
	}

}
