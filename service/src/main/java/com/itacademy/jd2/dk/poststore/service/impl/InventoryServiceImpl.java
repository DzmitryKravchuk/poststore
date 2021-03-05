package com.itacademy.jd2.dk.poststore.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.dk.poststore.dao.api.IInventoryDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IInventory;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.dao.api.filter.InventoryFilter;
import com.itacademy.jd2.dk.poststore.service.IInventoryService;

@Service
public class InventoryServiceImpl implements IInventoryService {
	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);

	private IInventoryDao dao;

	@Autowired
	public InventoryServiceImpl(IInventoryDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IInventory get(Integer id) {
		final IInventory entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IInventory> getAll() {
		final List<IInventory> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IInventory entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new inventory created: {}", entity);
		} else {
			LOGGER.debug("inventory updated: {}", entity);
			dao.update(entity);
		}
	}

	public void save(IInventory... entities) {
		Date modified = new Date();
		for (IInventory iInventory : entities) {

			iInventory.setUpdated(modified);
			iInventory.setCreated(modified);
		}
		dao.save(entities);

	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all inventories");
		dao.deleteAll();
	}

	@Override
	public IInventory createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IInventory> find(InventoryFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(InventoryFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IInventory getFullInfo(Integer id) {
		final IInventory entity = dao.getFullInfo(id);

		return entity;
	}

	@Override
	public IInventory getInventoryItem(IStore store, IProduct product) {
		IInventory entity = dao.getInventoryItem(store, product);
		if (entity.getStore() == null) {
			entity = dao.createEntity();
			final Date modifedOn = new Date();
			entity.setUpdated(modifedOn);
			entity.setCreated(modifedOn);
			entity.setStore(store);
			entity.setProduct(product);
			entity.setQuantity(0);
			entity.setVersion(0);
			dao.insert(entity);
			LOGGER.info("new inventoryItem created: {}", entity);
		}
		entity.setProduct(product);
		entity.setStore(store);
		return entity;
	}

	@Override
	public void save(List<IInventory> entities) {
		Date modified = new Date();
		for (IInventory iInventory : entities) {

			iInventory.setUpdated(modified);
			iInventory.setCreated(modified);
			save(iInventory);
		}

	}
}
