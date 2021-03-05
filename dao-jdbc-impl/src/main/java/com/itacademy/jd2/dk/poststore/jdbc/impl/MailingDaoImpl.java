package com.itacademy.jd2.dk.poststore.jdbc.impl;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.dk.poststore.dao.api.IMailingDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.MailingType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMailing;

@Repository
public abstract class MailingDaoImpl implements IMailingDao {



	private double getActualPrice(IMailing entity) {
		Double actualPrice = 0.0;
		Double price4g100 = 0.00;

		if (entity.getMailingType() == MailingType.express) {
			price4g100 = entity.getCountry().getExpressZone().getPrice4g100();
		} else if (entity.getMailingType() == MailingType.letter) {
			price4g100 = entity.getCountry().getLetterZone().getPrice4g100();
		} else if (entity.getMailingType() == MailingType.parcel) {
			price4g100 = entity.getCountry().getParcelZone().getPrice4g100();
		}

		actualPrice = Math.round((price4g100 * entity.getWeight() * 10) * 100.0) / 100.0;

		return actualPrice;
	}
}
