package com.itacademy.jd2.dk.poststore.jdbc.impl.entity;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccountDetails;

public class UserAccount extends BaseEntity implements IUserAccount {
	private UserRole userRole;
	private String eMail;
	private String password;

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
	public String toString() {
		return "UserAccount [geteMail()=" + geteMail() + ", getPassword()=" + getPassword() + ", getId()=" + getId()
				+ ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}

	@Override
	public IUserAccountDetails getUserAccountDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserAccountDetails(IUserAccountDetails userAccountDetails) {
		// TODO Auto-generated method stub

	}

}
