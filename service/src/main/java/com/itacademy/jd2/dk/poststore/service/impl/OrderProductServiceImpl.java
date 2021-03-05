package com.itacademy.jd2.dk.poststore.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.dk.poststore.dao.api.IOrderProductDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.ShippingType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.Status;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccountDetails;
import com.itacademy.jd2.dk.poststore.dao.api.filter.OrderProductFilter;
import com.itacademy.jd2.dk.poststore.service.IOrderProductService;

@Service
public class OrderProductServiceImpl implements IOrderProductService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderProductServiceImpl.class);

	private IOrderProductDao orderProductDao;

	@Autowired
	public OrderProductServiceImpl(IOrderProductDao dao) {
		super();
		this.orderProductDao = dao;
	}

	@Override
	public IOrderProduct get(Integer id) {
		final IOrderProduct entity = orderProductDao.get(id);
		return entity;
	}

	@Override
	public List<IOrderProduct> getAll() {
		final List<IOrderProduct> all = orderProductDao.selectAll();
		return all;
	}

	@Override
	public void save(IOrderProduct entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			orderProductDao.insert(entity);
			LOGGER.info("new orderProduct created: {}", entity);
		} else {
			LOGGER.debug("orderProduct updated: {}", entity);
			orderProductDao.update(entity);
		}
	}

	@Override
	public void save(IOrderProduct... entities) {
		Date modified = new Date();
		for (IOrderProduct iOrder : entities) {

			iOrder.setUpdated(modified);
			iOrder.setCreated(modified);
		}
		orderProductDao.save(entities);
	}

	@Override
	public void delete(Integer id) {
		orderProductDao.delete(id);

	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all orders");
		orderProductDao.deleteAll();

	}

	@Override
	public IOrderProduct createEntity() {
		return orderProductDao.createEntity();
	}

	@Override
	public List<IOrderProduct> find(OrderProductFilter filter) {
		return orderProductDao.find(filter);
	}

	@Override
	public long getCount(OrderProductFilter filter) {
		return orderProductDao.getCount(filter);
	}

	@Override
	public IOrderProduct getFullInfo(Integer id) {
		final IOrderProduct entity = orderProductDao.getFullInfo(id);
		return entity;
	}

	@Override
	public IOrderProduct getProductCart(IUserAccount userAccount) {
		IOrderProduct entity = orderProductDao.getProductCart(userAccount);
		if (entity.getStatus() != Status.productCart) {
			entity = orderProductDao.createEntity();
			final Date modifedOn = new Date();
			entity.setUpdated(modifedOn);
			entity.setCreated(modifedOn);
			entity.setStatus(Status.productCart);
			entity.setShippingType(ShippingType.parcelDelivery);
			entity.setCost(0.00);
			entity.setUserAccount(userAccount);

			final IUserAccountDetails userAccountDetails = userAccount.getUserAccountDetails();
			String address = userAccountDetails.getAdress();
			entity.setShippingTo(address);
			orderProductDao.insert(entity);
			LOGGER.info("new productCart created: {}", entity);
		}
		return entity;
	}

	@Override
	public List<IOrderProduct> getPreviousMonthOrders() {
		return orderProductDao.getPreviousMonthOrders();
	}

}
