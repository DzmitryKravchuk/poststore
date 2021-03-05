package com.itacademy.jd2.dk.poststore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.filter.OrderProductFilter;

public interface IOrderProductService {
	IOrderProduct get(Integer id);

	List<IOrderProduct> getAll();

	@Transactional
	void save(IOrderProduct entity);

	@Transactional
	void save(IOrderProduct... entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IOrderProduct createEntity();

	List<IOrderProduct> find(OrderProductFilter filter);

	long getCount(OrderProductFilter filter);

	IOrderProduct getFullInfo(Integer id);

	@Transactional
	IOrderProduct getProductCart(IUserAccount userAccount); // if null, create new entity

	List<IOrderProduct> getPreviousMonthOrders();
}
