package com.itacademy.jd2.dk.poststore.dao.api.filter;

public class InventoryFilter extends AbstractFilter {
	private boolean fetchProduct;
	private boolean fetchStore;

	public boolean isFetchProduct() {
		return fetchProduct;
	}

	public void setFetchProduct(boolean fetchProduct) {
		this.fetchProduct = fetchProduct;
	}

	public boolean isFetchStore() {
		return fetchStore;
	}

	public void setFetchStore(boolean fetchStore) {
		this.fetchStore = fetchStore;
	}

}
