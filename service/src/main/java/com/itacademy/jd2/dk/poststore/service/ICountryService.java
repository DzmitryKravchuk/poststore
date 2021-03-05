package com.itacademy.jd2.dk.poststore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICountry;
import com.itacademy.jd2.dk.poststore.dao.api.filter.CountryFilter;

public interface ICountryService {

	ICountry get(Integer id);
	
	ICountry getFullInfo(Integer id);

	List<ICountry> getAll();

	@Transactional
	void save(ICountry entity);

	@Transactional
	void save(ICountry... entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ICountry createEntity();

	List<ICountry> find(CountryFilter filter);

	long getCount(CountryFilter filter);
}
