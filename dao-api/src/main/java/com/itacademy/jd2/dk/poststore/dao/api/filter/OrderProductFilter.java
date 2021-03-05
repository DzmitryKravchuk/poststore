package com.itacademy.jd2.dk.poststore.dao.api.filter;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.Status;

public class OrderProductFilter extends AbstractFilter {
	private boolean fetchUserAccount;
	private Integer userAccountId;
	private Status status;

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isFetchUserAccount() {
		return fetchUserAccount;
	}

	public void setFetchUserAccount(final boolean fetchUserAccount) {
		this.fetchUserAccount = fetchUserAccount;
	}

}
