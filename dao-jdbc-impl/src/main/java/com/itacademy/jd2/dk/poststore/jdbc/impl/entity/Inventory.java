package com.itacademy.jd2.dk.poststore.jdbc.impl.entity;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IInventory;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;

public class Inventory extends BaseEntity implements IInventory {
	private IStore store;
	private IProduct product;
	private Integer quantity;

	@Override
	public IStore getStore() {
		return store;
	}

	@Override
	public void setStore(IStore store) {
		this.store = store;
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
	public Integer getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVersion(Integer version) {
		// TODO Auto-generated method stub

	}
}
