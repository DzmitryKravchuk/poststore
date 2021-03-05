package com.itacademy.jd2.dk.poststore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICourier;
import com.itacademy.jd2.dk.poststore.dao.api.filter.CourierFilter;

public interface ICourierService {
	ICourier get(Integer id);

	List<ICourier> getAll();

	@Transactional
	void save(ICourier entity);

	@Transactional
	void save(ICourier... entities);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ICourier createEntity();

	List<ICourier> find(CourierFilter filter);

	long getCount(CourierFilter filter);

	ICourier getFullInfo(Integer id);

}
