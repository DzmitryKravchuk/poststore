package com.itacademy.jd2.dk.poststore.dao.api;

import java.util.List;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IParcelZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ParcelZoneFilter;

public interface IParcelZoneDao extends IDao<IParcelZone, Integer> {

	void save(IParcelZone... entities);

	List<IParcelZone> find(ParcelZoneFilter filter);

	long getCount(ParcelZoneFilter filter);
}
