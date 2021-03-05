package com.itacademy.jd2.dk.poststore.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.dk.poststore.dao.api.IMoneyTransferDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMoneyTransfer;
import com.itacademy.jd2.dk.poststore.dao.api.filter.MoneyTransferFilter;
import com.itacademy.jd2.dk.poststore.service.IMoneyTransferService;

@Service
public class MoneyTransferServiceImpl implements IMoneyTransferService {
	private static final Double PERCENT = 3.0;

	private static final Logger LOGGER = LoggerFactory.getLogger(MoneyTransferServiceImpl.class);

	private IMoneyTransferDao dao;

	@Autowired
	public MoneyTransferServiceImpl(IMoneyTransferDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IMoneyTransfer get(Integer id) {
		final IMoneyTransfer entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IMoneyTransfer> getAll() {
		final List<IMoneyTransfer> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IMoneyTransfer entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			entity.setTransactionPrice(getActualPrice(entity));
			dao.insert(entity);
			LOGGER.info("new moneyTransfer created: {}", entity);
		} else {
			LOGGER.debug("moneyTransfer updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public void save(IMoneyTransfer... entities) {
		Date modified = new Date();
		for (IMoneyTransfer iMoneyTransfer : entities) {

			iMoneyTransfer.setUpdated(modified);
			iMoneyTransfer.setCreated(modified);
		}
		dao.save(entities);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);

	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all moneyTransfers");
		dao.deleteAll();
	}

	@Override
	public IMoneyTransfer createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IMoneyTransfer> find(MoneyTransferFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(MoneyTransferFilter filter) {
		return dao.getCount(filter);
	}

	private Double getActualPrice(IMoneyTransfer entity) {
		Double actualPrice = Math.round(entity.getAmount() * PERCENT / 100 * 100.0) / 100.0;
		return actualPrice;
	}

	@Override
	public IMoneyTransfer getFullInfo(Integer id) {
		final IMoneyTransfer entity = dao.getFullInfo(id);
		return entity;
	}
}
