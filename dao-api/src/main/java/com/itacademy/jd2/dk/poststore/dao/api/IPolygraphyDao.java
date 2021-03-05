package com.itacademy.jd2.dk.poststore.dao.api;

import java.util.List;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPolygraphy;
import com.itacademy.jd2.dk.poststore.dao.api.filter.PolygraphyFilter;

public interface IPolygraphyDao extends IDao<IPolygraphy, Integer> {

	List<IPolygraphy> find(PolygraphyFilter filter);

	long getCount(PolygraphyFilter filter);

	void save(IPolygraphy[] entities);

	IPolygraphy getFullInfo(Integer id);
}
