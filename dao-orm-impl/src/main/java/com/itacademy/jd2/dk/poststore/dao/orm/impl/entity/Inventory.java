package com.itacademy.jd2.dk.poststore.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IInventory;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;

@Entity
public class Inventory extends BaseEntity implements IInventory {

	@Column
	@Version
	private Integer version;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Store.class)
	private IStore store;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
	private IProduct product;

	@Column
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
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Inventory [getId()=" + getId() + ", product=" + getProduct().getName() + ", quantity=" + getQuantity()
				+ ", in store=" + getStore().getId() + "]";
	}
}
