package com.itacademy.jd2.dk.poststore.dao.api.filter;

public class CourierFilter extends AbstractFilter {
	private boolean fetchUserAccount;
	private Integer userAccountId;

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

	public final boolean isFetchUserAccount() {
		return fetchUserAccount;
	}

	public final void setFetchUserAccount(boolean fetchUserAccount) {
		this.fetchUserAccount = fetchUserAccount;
	}

}
