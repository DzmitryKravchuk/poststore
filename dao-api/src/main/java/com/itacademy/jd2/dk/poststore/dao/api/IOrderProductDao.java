package com.itacademy.jd2.dk.poststore.dao.api;

import java.util.List;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.filter.OrderProductFilter;

public interface IOrderProductDao extends IDao<IOrderProduct, Integer> {

	List<IOrderProduct> find(OrderProductFilter filter);

	long getCount(OrderProductFilter filter);

	void save(IOrderProduct[] entities);

	IOrderProduct getFullInfo(Integer id);

	IOrderProduct getProductCart(IUserAccount userAccount);

	List<IOrderProduct> getPreviousMonthOrders();

}
