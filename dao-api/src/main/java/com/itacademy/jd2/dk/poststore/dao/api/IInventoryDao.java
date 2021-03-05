package com.itacademy.jd2.dk.poststore.dao.api;

import java.util.List;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IInventory;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.dao.api.filter.InventoryFilter;

public interface IInventoryDao extends IDao<IInventory, Integer> {

	List<IInventory> find(InventoryFilter filter);

	long getCount(InventoryFilter filter);

	void save(IInventory[] entities);

	IInventory getFullInfo(Integer id);

	IInventory getInventoryItem(IStore store, IProduct product);

}
