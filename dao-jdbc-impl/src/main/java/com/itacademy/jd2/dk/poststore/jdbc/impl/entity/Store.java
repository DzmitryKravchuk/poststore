package com.itacademy.jd2.dk.poststore.jdbc.impl.entity;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.StoreType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;

public class Store extends BaseEntity implements IStore {

	@Override
	public String toString() {
		return "Store [name=" + getStoreType() + ", getId()=" + getId() + ", getCreated()=" + getCreated()
				+ ", getUpdated()=" + getUpdated() + "]";
	}

	@Override
	public void setStoreType(StoreType storeType) {
		// TODO Auto-generated method stub

	}

	@Override
	public StoreType getStoreType() {
		// TODO Auto-generated method stub
		return null;
	}
}
