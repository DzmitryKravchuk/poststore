package com.itacademy.jd2.dk.poststore.jdbc.impl.entity;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.Status;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.ShippingType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;

public class Orderr extends BaseEntity implements IOrderProduct {
	private IUserAccount customer;

	@Override
	public String toString() {
		return "Order [getId()=" + getId() + ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}

	@Override
	public IUserAccount getUserAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getShippingTo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShippingTo(String shippingTo) {
		// TODO Auto-generated method stub

	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCost(Double orderCost) {
		// TODO Auto-generated method stub

	}

	@Override
	public ShippingType getShippingType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShippingType(ShippingType shippingType) {
		// TODO Auto-generated method stub

	}

	@Override
	public Status getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStatus(Status orderStatus) {
		// TODO Auto-generated method stub

	}

}
