package com.itacademy.jd2.dk.poststore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IInventory;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.dao.api.filter.InventoryFilter;

public interface IInventoryService {
	IInventory get(Integer id);

	List<IInventory> getAll();

	@Transactional
	void save(IInventory inventory);

	@Transactional
	void save(List <IInventory> entities);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IInventory createEntity();

	List<IInventory> find(InventoryFilter filter);

	long getCount(InventoryFilter filter);

	IInventory getFullInfo(Integer id);

	@Transactional
	IInventory getInventoryItem(IStore store, IProduct product); // if null, create new entity

}
