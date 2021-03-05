package com.itacademy.jd2.dk.poststore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccountDetails;

public interface ICustomerDetailsService {
	IUserAccountDetails get(Integer id);

	List<IUserAccountDetails> getAll();

	@Transactional
	void save(IUserAccountDetails entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IUserAccountDetails createEntity();

}
