package com.itacademy.jd2.dk.poststore.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.StoreType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;

@Entity
public class Store extends BaseEntity implements IStore {

	@Column
	@Enumerated(EnumType.STRING)
	private StoreType storeType;

	@Override
	public StoreType getStoreType() {
		return storeType;
	}

	@Override
	public void setStoreType(StoreType storeType) {
		this.storeType = storeType;
	}

	@Override
	public String toString() {
		return "Store [name=" + getStoreType() + ", getId()=" + getId() + ", getCreated()=" + getCreated()
				+ ", getUpdated()=" + getUpdated() + "]";
	}
}
