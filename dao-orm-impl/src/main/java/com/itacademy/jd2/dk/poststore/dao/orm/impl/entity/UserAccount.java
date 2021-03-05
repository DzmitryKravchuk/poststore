package com.itacademy.jd2.dk.poststore.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccountDetails;

@Entity
public class UserAccount extends BaseEntity implements IUserAccount {

	@Column
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	@Column
	private String eMail;
	@Column
	private String password;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccount", targetEntity = UserAccountDetails.class)
	private IUserAccountDetails userAccountDetails;

	@Override
	public UserRole getUserRole() {
		return userRole;
	}

	@Override
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	@Override
	public String geteMail() {
		return eMail;
	}

	@Override
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public IUserAccountDetails getUserAccountDetails() {
		return userAccountDetails;
	}

	@Override
	public void setUserAccountDetails(IUserAccountDetails userAccountDetails) {
		this.userAccountDetails = userAccountDetails;
	}

	@Override
	public String toString() {
		return "UserAccount [geteMail()=" + geteMail() + ", getPassword()=" + getPassword() + ", getId()=" + getId()
				+ ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}
}
