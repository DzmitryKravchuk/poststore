package com.itacademy.jd2.dk.poststore.dao.api.entity.table;

public interface IOrderItem extends IBaseEntity {

	IProduct getProduct();

	void setProduct(IProduct product);

	Integer getQuantity();

	void setQuantity(Integer quantity);

	IOrderProduct getOrderProduct();

	void setOrderProduct(IOrderProduct orderProduct);

}
