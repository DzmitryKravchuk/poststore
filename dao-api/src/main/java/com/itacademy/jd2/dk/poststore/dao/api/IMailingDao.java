package com.itacademy.jd2.dk.poststore.dao.api;

import java.util.List;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMailing;
import com.itacademy.jd2.dk.poststore.dao.api.filter.MailingFilter;

public interface IMailingDao extends IDao<IMailing, Integer> {

	List<IMailing> find(MailingFilter filter);

	long getCount(MailingFilter filter);

	void save(IMailing[] entities);

	IMailing getFullInfo(Integer id);
}
