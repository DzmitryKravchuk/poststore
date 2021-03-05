package com.itacademy.jd2.dk.poststore.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.dk.poststore.dao.api.IUserAccountDetailsDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccountDetails;
import com.itacademy.jd2.dk.poststore.service.ICustomerDetailsService;

@Service
public class UserAccounDetailsServiceImpl implements ICustomerDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccounDetailsServiceImpl.class);

	private IUserAccountDetailsDao dao;

	@Override
	public IUserAccountDetails get(Integer id) {
		final IUserAccountDetails entity = dao.get(id);
		return entity;
	}

	@Autowired
	public UserAccounDetailsServiceImpl(IUserAccountDetailsDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public List<IUserAccountDetails> getAll() {
		final List<IUserAccountDetails> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IUserAccountDetails entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);

			dao.insert(entity);
			LOGGER.info("new userAccountDetails created: {}", entity);
		} else {
			LOGGER.debug("userAccountDetails updated: {}", entity);
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
	public IUserAccountDetails createEntity() {
		return dao.createEntity();
	}

}
