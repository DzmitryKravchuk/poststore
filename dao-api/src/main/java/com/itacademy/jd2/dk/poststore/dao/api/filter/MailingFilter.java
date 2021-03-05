package com.itacademy.jd2.dk.poststore.dao.api.filter;

public class MailingFilter extends AbstractFilter {
	
	private boolean fetchCountry;
	private boolean fetchUserAccount;
	private Integer userAccountId;
	
	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

	public boolean isFetchCountry() {
		return fetchCountry;
	}

	public void setFetchCountry(final boolean fetchCountry) {
		this.fetchCountry = fetchCountry;
	}

	public final boolean isFetchUserAccount() {
		return fetchUserAccount;
	}

	public final void setFetchUserAccount(boolean fetchUserAccount) {
		this.fetchUserAccount = fetchUserAccount;
	}


}
