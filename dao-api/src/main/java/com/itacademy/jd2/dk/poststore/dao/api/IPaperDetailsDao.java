package com.itacademy.jd2.dk.poststore.dao.api;

import java.util.List;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;
import com.itacademy.jd2.dk.poststore.dao.api.filter.PaperDetailsFilter;

public interface IPaperDetailsDao extends IDao<IPaperDetails, Integer> {

	List<IPaperDetails> find(PaperDetailsFilter filter);

	long getCount(PaperDetailsFilter filter);

}
