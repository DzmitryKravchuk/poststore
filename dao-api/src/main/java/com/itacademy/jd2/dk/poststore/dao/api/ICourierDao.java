package com.itacademy.jd2.dk.poststore.dao.api;

import java.util.List;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICourier;
import com.itacademy.jd2.dk.poststore.dao.api.filter.CourierFilter;

public interface ICourierDao extends IDao<ICourier, Integer> {

	List<ICourier> find(CourierFilter filter);

	long getCount(CourierFilter filter);

	void save(ICourier[] entities);

	ICourier getFullInfo(Integer id);
}
