package com.itacademy.jd2.dk.poststore.jdbc.impl.entity;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccountDetails;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;

public class CustomerDetails extends BaseEntity implements IUserAccountDetails {

	private IUserAccount userAccount;
	private String name;
	private String adress;
	private Integer phone;

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAdress() {
		return adress;
	}

	@Override
	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Override
	public Integer getPhone() {
		return phone;
	}

	@Override
	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "CustomerDetails [getName()=" + getName() + ", getAdress()=" + getAdress() + ", getPhone()=" + getPhone()
				+ "]";
	}
}
