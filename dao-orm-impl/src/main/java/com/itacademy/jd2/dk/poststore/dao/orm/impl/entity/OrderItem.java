package com.itacademy.jd2.dk.poststore.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderItem;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;

@Entity
public class OrderItem extends BaseEntity implements IOrderItem {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = OrderProduct.class)
	private IOrderProduct orderProduct;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
	private IProduct product;

	@Column
	private Integer quantity;

	@Override
	public IOrderProduct getOrderProduct() {
		return orderProduct;
	}

	@Override
	public void setOrderProduct(IOrderProduct orderProduct) {
		this.orderProduct = orderProduct;
	}

	@Override
	public IProduct getProduct() {
		return product;
	}

	@Override
	public void setProduct(IProduct product) {
		this.product = product;
	}

	@Override
	public Integer getQuantity() {
		return quantity;
	}

	@Override
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductCart [getId()=" + getId()

				+ ", getQuantity()=" + getQuantity() + ", getCreated()=" + getCreated() + ", getUpdated()="
				+ getUpdated() + "]";
	}
}
