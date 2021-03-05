package com.itacademy.jd2.dk.poststore.dao.api.entity.table;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.Status;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.ShippingType;

public interface IOrderProduct extends IBaseEntity {

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount userAccount);

	String getShippingTo();

	void setShippingTo(String shippingTo);

	Double getCost();

	void setCost(Double orderCost);

	ShippingType getShippingType();

	void setShippingType(ShippingType shippingType);

	Status getStatus();

	void setStatus(Status orderStatus);

}
