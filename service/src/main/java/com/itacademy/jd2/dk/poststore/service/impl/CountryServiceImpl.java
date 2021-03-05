package com.itacademy.jd2.dk.poststore.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.dk.poststore.dao.api.ICountryDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICountry;
import com.itacademy.jd2.dk.poststore.dao.api.filter.CountryFilter;
import com.itacademy.jd2.dk.poststore.service.ICountryService;

@Service
public class CountryServiceImpl implements ICountryService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);

	private ICountryDao dao;

	@Autowired
	public CountryServiceImpl(ICountryDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ICountry get(Integer id) {
		final ICountry entity = dao.get(id);
		return entity;
	}

	@Override
	public List<ICountry> getAll() {
		final List<ICountry> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(ICountry entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new country created: {}", entity);
		} else {
			LOGGER.debug("country updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public void save(ICountry... entities) {
		Date modified = new Date();
		for (ICountry iCountry : entities) {

			iCountry.setUpdated(modified);
			iCountry.setCreated(modified);
		}
		dao.save(entities);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all countries");
		dao.deleteAll();
	}

	@Override
	public ICountry createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<ICountry> find(CountryFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(CountryFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public ICountry getFullInfo(Integer id) {
		final ICountry entity = dao.getFullInfo(id);
		return entity;
	}

}
