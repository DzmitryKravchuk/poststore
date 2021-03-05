package com.itacademy.jd2.dk.poststore.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.dk.poststore.dao.api.IMailingDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.MailingType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMailing;
import com.itacademy.jd2.dk.poststore.dao.api.filter.MailingFilter;
import com.itacademy.jd2.dk.poststore.service.IMailingService;

@Service
public class MailingServiceImpl implements IMailingService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MailingServiceImpl.class);

	private IMailingDao dao;

	@Autowired
	public MailingServiceImpl(IMailingDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IMailing get(Integer id) {
		final IMailing entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IMailing> getAll() {
		final List<IMailing> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IMailing entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			entity.setPrice(getActualPrice(entity));
			dao.insert(entity);
			LOGGER.info("new mailing created: {}", entity);
		} else {
			LOGGER.debug("mailing updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public void save(IMailing... entities) {
		Date modified = new Date();
		for (IMailing iMailing : entities) {

			iMailing.setUpdated(modified);
			iMailing.setCreated(modified);
		}
		dao.save(entities);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);

	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all mailings");
		dao.deleteAll();

	}

	@Override
	public IMailing createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IMailing> find(MailingFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(MailingFilter filter) {
		return dao.getCount(filter);
	}

	private double getActualPrice(IMailing entity) {
		Double actualPrice = 0.0;
		Double price4g100 = 0.00;
		Double weight = 0.00;
		if (entity.getWeight() < 0.1) {
			weight = 0.1;
		} else {
			weight = entity.getWeight();
		}

		if (entity.getMailingType() == MailingType.express) {
			price4g100 = entity.getCountry().getExpressZone().getPrice4g100();
		} else if (entity.getMailingType() == MailingType.letter) {
			price4g100 = entity.getCountry().getLetterZone().getPrice4g100();
		} else if (entity.getMailingType() == MailingType.parcel) {
			price4g100 = entity.getCountry().getParcelZone().getPrice4g100();
		}

		actualPrice = Math.round((price4g100 * weight * 10) * 100.0) / 100.0;

		return actualPrice;
	}

	@Override
	public IMailing getFullInfo(Integer id) {
		final IMailing entity = dao.getFullInfo(id);

		return entity;
	}
}
