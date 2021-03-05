package com.itacademy.jd2.dk.poststore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccountDetails;
import com.itacademy.jd2.dk.poststore.dao.api.filter.UserAccountFilter;

public interface IUserAccountService {
	IUserAccount get(Integer id);

	List<IUserAccount> getAll();

	@Transactional
	void save(IUserAccount entity);

	@Transactional
	void save(IUserAccount... entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IUserAccount createEntity();

	List<IUserAccount> find(UserAccountFilter filter);

	long getCount(UserAccountFilter filter);

	IUserAccountDetails createUserAccountDetailsEntity();

	IUserAccount getUserByLogin(String username);

}
