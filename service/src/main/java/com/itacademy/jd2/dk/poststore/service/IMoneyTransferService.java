package com.itacademy.jd2.dk.poststore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMoneyTransfer;
import com.itacademy.jd2.dk.poststore.dao.api.filter.MoneyTransferFilter;

public interface IMoneyTransferService {
	IMoneyTransfer get(Integer id);

	List<IMoneyTransfer> getAll();

	@Transactional
	void save(IMoneyTransfer entity);

	@Transactional
	void save(IMoneyTransfer... entities);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IMoneyTransfer createEntity();

	List<IMoneyTransfer> find(MoneyTransferFilter filter);

	long getCount(MoneyTransferFilter filter);
	
	IMoneyTransfer getFullInfo(Integer id);

}
