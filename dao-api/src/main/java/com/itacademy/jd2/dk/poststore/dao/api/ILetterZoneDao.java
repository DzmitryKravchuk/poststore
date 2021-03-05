package com.itacademy.jd2.dk.poststore.dao.api;

import java.util.List;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ILetterZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.LetterZoneFilter;

public interface ILetterZoneDao extends IDao<ILetterZone, Integer> {

	void save(ILetterZone... entities);

	List<ILetterZone> find(LetterZoneFilter filter);

	long getCount(LetterZoneFilter filter);
}
