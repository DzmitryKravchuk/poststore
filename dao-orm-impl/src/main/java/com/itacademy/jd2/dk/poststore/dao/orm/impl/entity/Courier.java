package com.itacademy.jd2.dk.poststore.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICourier;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;

@Entity
public class Courier extends BaseEntity implements ICourier {

	@Column
	private String shippingFrom;
	@Column
	private String shippingTo;
	@Column
	private Double price;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
	private IUserAccount userAccount;

	@Column
	private String addressee;

	@Override
	public String getShippingFrom() {
		return shippingFrom;
	}

	@Override
	public void setShippingFrom(String shippingFrom) {
		this.shippingFrom = shippingFrom;
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
	public Double getPrice() {
		return price;
	}

	@Override
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public String getAddressee() {
		return addressee;
	}

	@Override
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	@Override
	public String toString() {
		return "Courier [getId()=" + getId() + ", getShippingFrom()=" + getShippingFrom() + ", getShippingTo()="
				+ getShippingTo() + ", getPrice()=" + getPrice() + ", getAddressee()=" + getAddressee() + "]";
	}

}
