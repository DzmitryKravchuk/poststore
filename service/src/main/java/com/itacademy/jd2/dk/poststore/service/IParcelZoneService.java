package com.itacademy.jd2.dk.poststore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IParcelZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ParcelZoneFilter;

public interface IParcelZoneService {
	IParcelZone get(Integer id);

	List<IParcelZone> getAll();

	@Transactional
	void save(IParcelZone entity);

	@Transactional
	void save(IParcelZone... entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IParcelZone createEntity();

	List<IParcelZone> find(ParcelZoneFilter filter);

	long getCount(ParcelZoneFilter filter);

}
