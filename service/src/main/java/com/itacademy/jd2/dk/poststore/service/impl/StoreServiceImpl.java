package com.itacademy.jd2.dk.poststore.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.dk.poststore.dao.api.IStoreDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.StoreType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.dao.api.filter.StoreFilter;
import com.itacademy.jd2.dk.poststore.service.IStoreService;

@Service
public class StoreServiceImpl implements IStoreService {
	private static final Logger LOGGER = LoggerFactory.getLogger(StoreServiceImpl.class);

	private IStoreDao dao;

	@Autowired
	public StoreServiceImpl(IStoreDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IStore get(Integer id) {
		final IStore entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IStore> getAll() {
		final List<IStore> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IStore entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new store created: {}", entity);
		} else {
			LOGGER.debug("store updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public void save(IStore... entities) {
		Date modified = new Date();
		for (IStore iStore : entities) {

			iStore.setUpdated(modified);
			iStore.setCreated(modified);
		}
		dao.save(entities);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all stores");
		dao.deleteAll();
	}

	@Override
	public IStore createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IStore> find(StoreFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(StoreFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IStore getStoreByType(StoreType storeType) {
		IStore entity = dao.getStoreByType(storeType);
		if (entity.getStoreType() == null) {
			entity = dao.createEntity();
			final Date modifedOn = new Date();
			entity.setUpdated(modifedOn);
			entity.setCreated(modifedOn);
			entity.setStoreType(storeType);
			dao.insert(entity);
			LOGGER.info("new store created: {}", entity);
		}
		return entity;
	}
}
