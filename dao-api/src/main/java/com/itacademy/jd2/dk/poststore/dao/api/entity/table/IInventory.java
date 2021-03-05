package com.itacademy.jd2.dk.poststore.dao.api.entity.table;

public interface IInventory extends IBaseEntity {

	IStore getStore();

	void setStore(IStore store);

	IProduct getProduct();

	void setProduct(IProduct product);

	Integer getQuantity();

	void setQuantity(Integer quantity);

	Integer getVersion();

	void setVersion(Integer version);

}
