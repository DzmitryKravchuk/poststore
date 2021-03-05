package com.itacademy.jd2.dk.poststore.dao.api.filter;

public class OrderItemFilter extends AbstractFilter {
	private boolean fetchProduct;
	private boolean fetchOrderProduct;
	private Integer orderProductId;

	public boolean isFetchProduct() {
		return fetchProduct;
	}

	public void setFetchProduct(boolean fetchProduct) {
		this.fetchProduct = fetchProduct;
	}

	public boolean isFetchOrderProduct() {
		return fetchOrderProduct;
	}

	public void setFetchOrderProduct(boolean fetchOrderProduct) {
		this.fetchOrderProduct = fetchOrderProduct;
	}

	public Integer getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(Integer orderProductId) {
		this.orderProductId = orderProductId;
	}

}
