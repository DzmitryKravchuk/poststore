package com.itacademy.jd2.dk.poststore.dao.api;

import java.util.List;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.filter.UserAccountFilter;

public interface IUserAccountDao extends IDao<IUserAccount, Integer> {

	List<IUserAccount> find(UserAccountFilter filter);

	long getCount(UserAccountFilter filter);

	void save(IUserAccount[] entities);

	IUserAccount getUserByLogin(String username);
}
