package com.itacademy.jd2.dk.poststore.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.dk.poststore.dao.api.IOrderItemDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderItem;
import com.itacademy.jd2.dk.poststore.dao.api.filter.OrderItemFilter;
import com.itacademy.jd2.dk.poststore.service.IOrderItemService;

@Service
public class OrderItemServiceImpl implements IOrderItemService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderItemServiceImpl.class);

	private IOrderItemDao itemDao;

	@Autowired
	public OrderItemServiceImpl(IOrderItemDao dao) {
		super();
		this.itemDao = dao;
	}

	@Override
	public IOrderItem get(Integer id) {
		final IOrderItem entity = itemDao.get(id);
		return entity;
	}

	@Override
	public List<IOrderItem> getAll() {
		final List<IOrderItem> all = itemDao.selectAll();
		return all;
	}

	@Override
	public void save(IOrderItem entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			itemDao.insert(entity);
			LOGGER.info("new orderItem created: {}", entity);
		} else {
			LOGGER.debug("orderItem updated: {}", entity);
			itemDao.update(entity);
		}
	}

	@Override
	public void save(IOrderItem[] entities) {
		Date modified = new Date();
		for (IOrderItem iProductItem : entities) {

			iProductItem.setUpdated(modified);
			iProductItem.setCreated(modified);
		}
		itemDao.save(entities);
	}

	@Override
	public void delete(Integer id) {
		itemDao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all orderItems");
		itemDao.deleteAll();
	}

	@Override
	public IOrderItem createEntity() {
		return itemDao.createEntity();
	}

	@Override
	public List<IOrderItem> find(OrderItemFilter filter) {
		return itemDao.find(filter);
	}

	@Override
	public long getCount(OrderItemFilter filter) {
		return itemDao.getCount(filter);
	}

	@Override
	public IOrderItem getFullInfo(Integer id) {
		final IOrderItem entity = itemDao.getFullInfo(id);
		return entity;
	}

}
