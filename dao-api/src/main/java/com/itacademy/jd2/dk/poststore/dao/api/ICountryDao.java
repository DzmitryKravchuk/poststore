package com.itacademy.jd2.dk.poststore.dao.api;

import java.util.List;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICountry;
import com.itacademy.jd2.dk.poststore.dao.api.filter.CountryFilter;

public interface ICountryDao extends IDao<ICountry, Integer> {

	void save(ICountry... entities);

	List<ICountry> find(CountryFilter filter);

	long getCount(CountryFilter filter);

	ICountry getFullInfo(Integer id);
}
