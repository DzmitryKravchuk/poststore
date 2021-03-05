package com.itacademy.jd2.dk.poststore.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.dk.poststore.dao.api.IParcelZoneDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IParcelZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ParcelZoneFilter;
import com.itacademy.jd2.dk.poststore.service.IParcelZoneService;

@Service
public class ParcelZoneServiceImpl implements IParcelZoneService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParcelZoneServiceImpl.class);

	private IParcelZoneDao dao;

	@Autowired
	public ParcelZoneServiceImpl(IParcelZoneDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IParcelZone get(Integer id) {
		final IParcelZone entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IParcelZone> getAll() {
		final List<IParcelZone> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IParcelZone entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new parcelZone created: {}", entity);
		} else {
			LOGGER.debug("parcelZone updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public void save(IParcelZone... entities) {
		Date modified = new Date();
		for (IParcelZone iParcelzone : entities) {

			iParcelzone.setUpdated(modified);
			iParcelzone.setCreated(modified);
		}
		dao.save(entities);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all parcelZones");
		dao.deleteAll();
	}

	@Override
	public IParcelZone createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IParcelZone> find(ParcelZoneFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(ParcelZoneFilter filter) {
		return dao.getCount(filter);
	}

}
