package com.itacademy.jd2.dk.poststore.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.MailingType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICountry;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMailing;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;

@Entity
public class Mailing extends BaseEntity implements IMailing {

	@Column
	@Enumerated(EnumType.STRING)
	private MailingType mailingType;

	@Column
	private Double weight;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
	private IUserAccount userAccount;

	@Column
	private String addressee;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
	private ICountry country;

	@Column
	private String address;
	@Column
	private Double price;

	@Override
	public MailingType getMailingType() {
		return mailingType;
	}

	@Override
	public void setMailingType(MailingType type) {
		this.mailingType = type;
	}

	@Override
	public Double getWeight() {
		return weight;
	}

	@Override
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Override
	public final IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public final void setUserAccount(IUserAccount userAccount) {
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
	public ICountry getCountry() {
		return country;
	}

	@Override
	public void setCountry(ICountry country) {
		this.country = country;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
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
	public String toString() {
		return "Mailing [getType()=" + getMailingType() + ", getId()=" + getId() + ", getWeight()=" + getWeight()
				+ ", getAddressee()=" + getAddressee() + ", getCountry()=" + getCountry() + ", getAddress()="
				+ getAddress() + ", getPrice()=" + getPrice() + ", getCreated()=" + getCreated() + ", getUpdated()="
				+ getUpdated() + "]";
	}
}
