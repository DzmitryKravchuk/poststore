package com.itacademy.jd2.dk.poststore.dao.api;

import java.util.List;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ExpressZoneFilter;

public interface IExpressZoneDao extends IDao<IExpressZone, Integer> {

	void save(IExpressZone... entities);

	List<IExpressZone> find(ExpressZoneFilter filter);

	long getCount(ExpressZoneFilter filter);
}
