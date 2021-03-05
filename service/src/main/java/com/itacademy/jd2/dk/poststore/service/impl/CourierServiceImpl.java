package com.itacademy.jd2.dk.poststore.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.dk.poststore.dao.api.ICourierDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICourier;
import com.itacademy.jd2.dk.poststore.dao.api.filter.CourierFilter;
import com.itacademy.jd2.dk.poststore.service.ICourierService;

@Service
public class CourierServiceImpl implements ICourierService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CourierServiceImpl.class);
	private static final Double ACTUALPRICE = 9.0;
	private ICourierDao dao;

	@Autowired
	public CourierServiceImpl(ICourierDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ICourier get(Integer id) {
		final ICourier entity = dao.get(id);
		return entity;
	}

	@Override
	public List<ICourier> getAll() {
		final List<ICourier> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(ICourier entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			entity.setPrice(ACTUALPRICE);
			dao.insert(entity);
			LOGGER.info("new courier created: {}", entity);
		} else {
			LOGGER.debug("courier updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public void save(ICourier... entities) {
		Date modified = new Date();
		for (ICourier ICourier : entities) {

			ICourier.setUpdated(modified);
			ICourier.setCreated(modified);
		}
		dao.save(entities);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all couriers");
		dao.deleteAll();
	}

	@Override
	public ICourier createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<ICourier> find(CourierFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(CourierFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public ICourier getFullInfo(Integer id) {
		final ICourier entity = dao.getFullInfo(id);
		return entity;
	}

}
