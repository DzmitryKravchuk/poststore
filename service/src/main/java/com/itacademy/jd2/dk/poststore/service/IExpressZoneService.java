package com.itacademy.jd2.dk.poststore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ExpressZoneFilter;

public interface IExpressZoneService {
	IExpressZone get(Integer id);

	List<IExpressZone> getAll();

	@Transactional
	void save(IExpressZone entity);

	@Transactional
	void save(IExpressZone... entities);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IExpressZone createEntity();

	List<IExpressZone> find(ExpressZoneFilter filter);

	long getCount(ExpressZoneFilter filter);

}
