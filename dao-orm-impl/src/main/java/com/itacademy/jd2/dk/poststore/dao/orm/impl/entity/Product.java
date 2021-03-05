package com.itacademy.jd2.dk.poststore.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;

@Entity
public class Product extends BaseEntity implements IProduct {

	@Column
	private String name;

	@Column
	private Double price;

	public Product(String name, Double price, Integer id) {
		super();
		setId(id);
		this.name = name;
		this.price = price;
	}

	public Product() {
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
	public Double getPrice() {
		return price;
	}

	@Override
	public void setPrice(Double price) {
		this.price = price;
	}

}
