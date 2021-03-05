package com.itacademy.jd2.dk.poststore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.StoreType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.dao.api.filter.StoreFilter;

public interface IStoreService {
	IStore get(Integer id);

	List<IStore> getAll();

	@Transactional
	void save(IStore entity);

	@Transactional
	void save(IStore... entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IStore createEntity();

	List<IStore> find(StoreFilter filter);

	long getCount(StoreFilter filter);

	@Transactional
	IStore getStoreByType(StoreType storeType);

}
