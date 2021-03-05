package com.itacademy.jd2.dk.poststore.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.ShippingType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.Status;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;

@Entity
public class OrderProduct extends BaseEntity implements IOrderProduct {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
	private IUserAccount userAccount;

	@Column
	private String shippingTo;

	@Column
	private Double cost;

	@Column
	@Enumerated(EnumType.STRING)
	private ShippingType shippingType;

	@Column
	@Enumerated(EnumType.STRING)
	private Status status;

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public String getShippingTo() {
		return shippingTo;
	}

	@Override
	public void setShippingTo(String shippingTo) {
		this.shippingTo = shippingTo;
	}

	@Override
	public Double getCost() {
		return cost;
	}

	@Override
	public void setCost(Double orderCost) {
		this.cost = orderCost;
	}

	@Override
	public ShippingType getShippingType() {
		return shippingType;
	}

	@Override
	public void setShippingType(ShippingType shippingType) {
		this.shippingType = shippingType;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public void setStatus(Status orderStatus) {
		this.status = orderStatus;
	}

	@Override
	public String toString() {
		return "OrderProduct [getId()=" + getId() + ", getShippingTo()=" + getShippingTo() + ", getOrderCost()="
				+ getCost() + ", getShippingType()=" + getShippingType() + ", getOrderStatus()=" + getStatus()
				+ ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}
}
