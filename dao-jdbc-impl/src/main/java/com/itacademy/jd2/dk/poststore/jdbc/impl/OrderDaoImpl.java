package com.itacademy.jd2.dk.poststore.jdbc.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.dk.poststore.dao.api.IOrderProductDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.filter.OrderProductFilter;

@Repository
public class OrderDaoImpl implements IOrderProductDao {

	@Override
	public IOrderProduct getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IOrderProduct createEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(IOrderProduct entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(IOrderProduct entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<IOrderProduct> find(OrderProductFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(OrderProductFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void save(IOrderProduct[] entities) {
		// TODO Auto-generated method stub

	}

	protected String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IOrderProduct get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<IOrderProduct> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IOrderProduct getProductCart(IUserAccount userAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IOrderProduct> getPreviousMonthOrders() {
		// TODO Auto-generated method stub
		return null;
	}

}
