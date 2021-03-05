package com.itacademy.jd2.dk.poststore.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.dk.poststore.dao.api.ILetterZoneDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ILetterZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.LetterZoneFilter;
import com.itacademy.jd2.dk.poststore.service.ILetterZoneService;

@Service
public class LetterZoneServiceImpl2 implements ILetterZoneService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LetterZoneServiceImpl2.class);

	private ILetterZoneDao dao;

	@Autowired
	public LetterZoneServiceImpl2(ILetterZoneDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ILetterZone get(Integer id) {
		final ILetterZone entity = dao.get(id);
		return entity;
	}

	@Override
	public List<ILetterZone> getAll() {
		final List<ILetterZone> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(ILetterZone entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new letterZone created: {}", entity);
		} else {
			LOGGER.debug("letterZone updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public void save(ILetterZone... entities) {
		Date modified = new Date();
		for (ILetterZone iLetterzone : entities) {

			iLetterzone.setUpdated(modified);
			iLetterzone.setCreated(modified);
		}
		dao.save(entities);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all letterZones");
		dao.deleteAll();
	}

	@Override
	public ILetterZone createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<ILetterZone> find(LetterZoneFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(LetterZoneFilter filter) {
		return dao.getCount(filter);
	}

}
