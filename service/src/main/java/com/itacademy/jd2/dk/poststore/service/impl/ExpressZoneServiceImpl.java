package com.itacademy.jd2.dk.poststore.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.dk.poststore.dao.api.IExpressZoneDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ExpressZoneFilter;
import com.itacademy.jd2.dk.poststore.service.IExpressZoneService;

@Service
public class ExpressZoneServiceImpl implements IExpressZoneService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExpressZoneServiceImpl.class);

	private IExpressZoneDao dao;

	@Autowired
	public ExpressZoneServiceImpl(IExpressZoneDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IExpressZone get(final Integer id) {
		final IExpressZone entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IExpressZone> getAll() {
		final List<IExpressZone> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(final IExpressZone entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new expressZone created: {}", entity);
		} else {
			LOGGER.debug("expressZone updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public void save(IExpressZone... entities) {
		Date modified = new Date();
		for (IExpressZone iExpresszone : entities) {

			iExpresszone.setUpdated(modified);
			iExpresszone.setCreated(modified);
		}
		dao.save(entities);
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all expressZones");
		dao.deleteAll();
	}

	@Override
	public IExpressZone createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IExpressZone> find(ExpressZoneFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(ExpressZoneFilter filter) {
		return dao.getCount(filter);
	}

}
