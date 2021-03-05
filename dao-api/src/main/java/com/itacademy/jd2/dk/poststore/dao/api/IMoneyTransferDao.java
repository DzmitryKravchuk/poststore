package com.itacademy.jd2.dk.poststore.dao.api;

import java.util.List;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMoneyTransfer;
import com.itacademy.jd2.dk.poststore.dao.api.filter.MoneyTransferFilter;

public interface IMoneyTransferDao extends IDao<IMoneyTransfer, Integer> {

	List<IMoneyTransfer> find(MoneyTransferFilter filter);

	long getCount(MoneyTransferFilter filter);

	void save(IMoneyTransfer[] entities);
	
	IMoneyTransfer getFullInfo(Integer id);
}
