package com.itacademy.jd2.dk.poststore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ProductFilter;

public interface IProductService {

	IProduct get(Integer id);

	List<IProduct> getAll();

	@Transactional
	void save(IProduct entity);

	@Transactional
	void save(IProduct... entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IProduct createEntity();

	List<IProduct> find(ProductFilter filter);

	long getCount(ProductFilter filter);

}
