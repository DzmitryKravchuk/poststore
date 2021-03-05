package com.itacademy.jd2.dk.poststore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;
import com.itacademy.jd2.dk.poststore.dao.api.filter.PaperDetailsFilter;

public interface IPaperDetailsService {
	IPaperDetails get(Integer id);

	List<IPaperDetails> getAll();

	@Transactional
	void save(IPaperDetails entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IPaperDetails createEntity();

	List<IPaperDetails> find(PaperDetailsFilter filter);

	long getCount(PaperDetailsFilter filter);

}
