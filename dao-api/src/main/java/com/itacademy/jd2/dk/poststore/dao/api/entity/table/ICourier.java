package com.itacademy.jd2.dk.poststore.dao.api.entity.table;

public interface ICourier extends IBaseEntity {

	String getShippingFrom();

	void setShippingFrom(String shippingFrom);

	String getShippingTo();

	void setShippingTo(String shippingTo);

	Double getPrice();

	void setPrice(Double price);

	String getAddressee();

	void setAddressee(String addressee);

	void setUserAccount(IUserAccount userAccount);

	IUserAccount getUserAccount();

}
