package edu.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.dao.BrandDAO;
import edu.poly.entity.Brand;
import edu.poly.service.BrandService;
@Service
public class BrandServiceImpl implements BrandService{
	@Autowired
	BrandDAO brandDAO;

	public BrandServiceImpl(BrandDAO brandDAO) {
		this.brandDAO = brandDAO;
	}

	@Override
	public <S extends Brand> S save(S entity) {
		return brandDAO.save(entity);
	}

	@Override
	public <S extends Brand> Optional<S> findOne(Example<S> example) {
		return brandDAO.findOne(example);
	}

	@Override
	public Page<Brand> findAll(Pageable pageable) {
		return brandDAO.findAll(pageable);
	}

	@Override
	public List<Brand> findAll() {
		return brandDAO.findAll();
	}

	@Override
	public List<Brand> findAll(Sort sort) {
		return brandDAO.findAll(sort);
	}

	@Override
	public List<Brand> findAllById(Iterable<Integer> ids) {
		return brandDAO.findAllById(ids);
	}

	@Override
	public Brand findById(Integer id) {
		return brandDAO.findById(id).get();
	}

	@Override
	public <S extends Brand> List<S> saveAll(Iterable<S> entities) {
		return brandDAO.saveAll(entities);
	}

	@Override
	public void flush() {
		brandDAO.flush();
	}

	@Override
	public <S extends Brand> S saveAndFlush(S entity) {
		return brandDAO.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return brandDAO.existsById(id);
	}

	@Override
	public <S extends Brand> List<S> saveAllAndFlush(Iterable<S> entities) {
		return brandDAO.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Brand> Page<S> findAll(Example<S> example, Pageable pageable) {
		return brandDAO.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Brand> entities) {
		brandDAO.deleteInBatch(entities);
	}

	@Override
	public <S extends Brand> long count(Example<S> example) {
		return brandDAO.count(example);
	}

	@Override
	public <S extends Brand> boolean exists(Example<S> example) {
		return brandDAO.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Brand> entities) {
		brandDAO.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return brandDAO.count();
	}

	@Override
	public void deleteById(Integer id) {
		brandDAO.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		brandDAO.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Brand entity) {
		brandDAO.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		brandDAO.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		brandDAO.deleteAllInBatch();
	}

	@Override
	public Brand getOne(Integer id) {
		return brandDAO.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Brand> entities) {
		brandDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		brandDAO.deleteAll();
	}

	@Override
	public Brand getById(Integer id) {
		return brandDAO.getById(id);
	}

	@Override
	public <S extends Brand> List<S> findAll(Example<S> example) {
		return brandDAO.findAll(example);
	}

	@Override
	public <S extends Brand> List<S> findAll(Example<S> example, Sort sort) {
		return brandDAO.findAll(example, sort);
	}

	@Override
	public Brand create(Brand brand) {
		// TODO Auto-generated method stub
		return brandDAO.save(brand);
	}

	@Override
	public Brand update(Brand brand) {
		// TODO Auto-generated method stub
		return brandDAO.save(brand);
	}

	@Override
	public void delete(Integer id) {
		brandDAO.deleteById(id);
		
	}

	@Override
	public List<Brand> getListBrand(String valued) {
		
		return brandDAO.getListBrand(valued);
	}
	
	
}
