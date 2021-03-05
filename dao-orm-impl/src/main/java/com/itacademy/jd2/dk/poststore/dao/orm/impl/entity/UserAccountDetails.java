package com.itacademy.jd2.dk.poststore.dao.orm.impl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccountDetails;

@Entity
public class UserAccountDetails implements IUserAccountDetails {

	@Id
	private Integer id;

	@Column(updatable = false)
	private Date created;

	@Column
	private Date updated;

	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = UserAccount.class)
	@PrimaryKeyJoinColumn
	private IUserAccount userAccount;

	@Column
	private String name;
	@Column
	private String adress;
	@Column
	private Integer phone;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public Date getUpdated() {
		return updated;
	}

	@Override
	public void setUpdated(Date updated) {
		this.updated = updated;
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
