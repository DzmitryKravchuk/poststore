package com.itacademy.jd2.dk.poststore.dao.api.entity.table;

public interface IProduct extends IBaseEntity {

	String getName();

	void setName(String name);

	Double getPrice();

	void setPrice(Double price);
}
