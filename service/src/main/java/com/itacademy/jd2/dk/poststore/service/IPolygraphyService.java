package com.itacademy.jd2.dk.poststore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPolygraphy;
import com.itacademy.jd2.dk.poststore.dao.api.filter.PolygraphyFilter;

public interface IPolygraphyService {
	IPolygraphy get(Integer id);

	List<IPolygraphy> getAll();

	@Transactional
	void save(IPolygraphy entity);

	@Transactional
	void save(IPolygraphy... entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IPolygraphy createEntity();

	List<IPolygraphy> find(PolygraphyFilter filter);

	long getCount(PolygraphyFilter filter);

	IPolygraphy getFullInfo(Integer id);

}
