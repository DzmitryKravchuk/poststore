package com.itacademy.jd2.dk.poststore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderItem;
import com.itacademy.jd2.dk.poststore.dao.api.filter.OrderItemFilter;

public interface IOrderItemService {
	IOrderItem get(Integer id);

	IOrderItem getFullInfo(Integer id);

	List<IOrderItem> getAll();

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IOrderItem createEntity();

	List<IOrderItem> find(OrderItemFilter filter);

	long getCount(OrderItemFilter filter);

	@Transactional
	void save(IOrderItem entity);

	@Transactional
	void save(IOrderItem[] entities);

}
