package com.itacademy.jd2.dk.poststore.dao.api;

import java.util.List;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderItem;
import com.itacademy.jd2.dk.poststore.dao.api.filter.OrderItemFilter;

public interface IOrderItemDao extends IDao<IOrderItem, Integer> {

	List<IOrderItem> find(OrderItemFilter filter);

	long getCount(OrderItemFilter filter);

	void save(IOrderItem[] entities);

	IOrderItem getFullInfo(Integer id);

}
