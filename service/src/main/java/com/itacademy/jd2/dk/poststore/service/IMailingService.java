package com.itacademy.jd2.dk.poststore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMailing;
import com.itacademy.jd2.dk.poststore.dao.api.filter.MailingFilter;

public interface IMailingService {
	IMailing get(Integer id);

	List<IMailing> getAll();

	@Transactional
	void save(IMailing entity);

	@Transactional
	void save(IMailing... entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IMailing createEntity();

	List<IMailing> find(MailingFilter filter);

	long getCount(MailingFilter filter);

	IMailing getFullInfo(Integer id);

}
