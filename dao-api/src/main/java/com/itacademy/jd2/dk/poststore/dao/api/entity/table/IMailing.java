package com.itacademy.jd2.dk.poststore.dao.api.entity.table;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.MailingType;

public interface IMailing extends IBaseEntity {

	MailingType getMailingType();

	void setMailingType(MailingType type);

	Double getWeight();

	void setWeight(Double weight);

	String getAddressee();

	void setAddressee(String addressee);

	ICountry getCountry();

	void setCountry(ICountry country);

	String getAddress();

	void setAddress(String address);

	Double getPrice();

	void setPrice(Double price);

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount userAccount);

}
