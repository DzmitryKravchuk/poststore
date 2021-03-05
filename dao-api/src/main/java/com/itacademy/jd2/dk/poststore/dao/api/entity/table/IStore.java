package com.itacademy.jd2.dk.poststore.dao.api.entity.table;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.StoreType;

public interface IStore extends IBaseEntity {

	void setStoreType(StoreType storeType);

	StoreType getStoreType();

}
