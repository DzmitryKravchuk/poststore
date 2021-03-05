package com.itacademy.jd2.dk.poststore.dao.api;

import java.util.List;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.StoreType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.dao.api.filter.StoreFilter;

public interface IStoreDao extends IDao<IStore, Integer> {

	List<IStore> find(StoreFilter filter);

	long getCount(StoreFilter filter);

	void save(IStore[] entities);

	IStore getStoreByType(StoreType storeType);
}
