package com.itacademy.jd2.dk.poststore.dao.api.filter;

public class CountryFilter extends AbstractFilter {
	private boolean fetchExpressZone;
	private boolean fetchLetterZone;
	private boolean fetchParcelZone;

	public boolean isFetchExpressZone() {
		return fetchExpressZone;
	}

	public void setFetchExpressZone(final boolean fetchExpressZone) {
		this.fetchExpressZone = fetchExpressZone;
	}

	public boolean isFetchLetterZone() {
		return fetchLetterZone;
	}

	public void setFetchLetterZone(final boolean fetchLetterZone) {
		this.fetchLetterZone = fetchLetterZone;
	}

	public boolean isFetchParcelZone() {
		return fetchParcelZone;
	}

	public void setFetchParcelZone(final boolean fetchParcelZone) {
		this.fetchParcelZone = fetchParcelZone;
	}

}
