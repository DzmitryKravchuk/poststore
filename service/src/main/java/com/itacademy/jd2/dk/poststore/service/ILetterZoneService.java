package com.itacademy.jd2.dk.poststore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ILetterZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.LetterZoneFilter;

public interface ILetterZoneService {
	ILetterZone get(Integer id);

	List<ILetterZone> getAll();

	@Transactional
	void save(ILetterZone entity);

	@Transactional
	void save(ILetterZone... entities);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ILetterZone createEntity();

	List<ILetterZone> find(LetterZoneFilter filter);

	long getCount(LetterZoneFilter filter);

}
