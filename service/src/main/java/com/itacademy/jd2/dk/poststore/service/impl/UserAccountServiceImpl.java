package com.itacademy.jd2.dk.poststore.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.dk.poststore.dao.api.IUserAccountDao;
import com.itacademy.jd2.dk.poststore.dao.api.IUserAccountDetailsDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccountDetails;
import com.itacademy.jd2.dk.poststore.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.dk.poststore.service.IUserAccountService;

@Service
public class UserAccountServiceImpl implements IUserAccountService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);

	private IUserAccountDao dao;
	private IUserAccountDetailsDao userAccountDetailsDao;

	@Autowired
	public UserAccountServiceImpl(IUserAccountDao dao, IUserAccountDetailsDao userAccountDetailsDao) {
		super();
		this.dao = dao;
		this.userAccountDetailsDao = userAccountDetailsDao;
	}

//	@Autowired
//	public UserAccountServiceImpl(IUserAccountDao dao, ICustomerDetailsDao customerDetailsDao) {
//		super();
//		this.dao = dao;
//		this.customerDetailsDao = customerDetailsDao;
//	}

	@Override
	public IUserAccount createEntity() {
		return dao.createEntity();
	}

	@Override
	public IUserAccountDetails createUserAccountDetailsEntity() {
		return userAccountDetailsDao.createEntity();
	}

	@Override
	public IUserAccount get(Integer id) {
		final IUserAccount entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IUserAccount> getAll() {
		final List<IUserAccount> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(final IUserAccount userAccount) {
		final Date modifiedDate = new Date();
		userAccount.setUpdated(modifiedDate);

		IUserAccountDetails details = userAccount.getUserAccountDetails();
		if (details != null) {
			details.setUpdated(modifiedDate);
		}
		if (userAccount.getId() == null) {
			userAccount.setCreated(modifiedDate);
			dao.insert(userAccount);

			if (details != null) {
				details.setId(userAccount.getId());
				details.setCreated(modifiedDate);
				details.setUserAccount(userAccount);
				userAccountDetailsDao.insert(details);
			}
			LOGGER.info("new userAccount created: {}", userAccount);
		} else {
			dao.update(userAccount);
			if (details != null) {
				userAccountDetailsDao.update(details);
				LOGGER.info("new userAccount updated: {}", userAccount);
			}
		}
	}

	@Override
	public void save(IUserAccount... entities) {
		Date modified = new Date();
		for (IUserAccount iUserAccount : entities) {

			iUserAccount.setUpdated(modified);
			iUserAccount.setCreated(modified);
		}
		dao.save(entities);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all userAccounts");
		dao.deleteAll();
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IUserAccount getUserByLogin(String username) {
		return dao.getUserByLogin(username);

	}

}
