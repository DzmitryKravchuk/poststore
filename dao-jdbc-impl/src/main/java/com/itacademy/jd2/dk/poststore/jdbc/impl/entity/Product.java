package com.itacademy.jd2.dk.poststore.jdbc.impl.entity;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;

public class Product extends BaseEntity implements IProduct {

	private String name;
	private Double price;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public Double getPrice() {
		return price;
	}

	@Override
	public void setPrice(final Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [name=" + getName() + ", getId()=" + getId() + ", getPrice()=" + getPrice() + ", getCreated()="
				+ getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}

}
