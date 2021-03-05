package com.itacademy.jd2.dk.poststore.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.dk.poststore.dao.api.IPaperDetailsDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;
import com.itacademy.jd2.dk.poststore.dao.api.filter.PaperDetailsFilter;
import com.itacademy.jd2.dk.poststore.service.IPaperDetailsService;

@Service
public class PaperDetailsServiceImpl implements IPaperDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaperDetailsServiceImpl.class);

	private IPaperDetailsDao dao;

	@Autowired
	public PaperDetailsServiceImpl(IPaperDetailsDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IPaperDetails get(Integer id) {
		final IPaperDetails entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IPaperDetails> getAll() {
		final List<IPaperDetails> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IPaperDetails entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new paperDetails created: {}", entity);
		} else {
			LOGGER.debug("paperDetails updated: {}", entity);
			dao.update(entity);
		}
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
	public IPaperDetails createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IPaperDetails> find(PaperDetailsFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(PaperDetailsFilter filter) {
		return dao.getCount(filter);
	}

}
