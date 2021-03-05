package com.itacademy.jd2.dk.poststore.dao.api;

import java.util.List;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ProductFilter;

public interface IProductDao extends IDao<IProduct, Integer> {

	void save(IProduct... entities);

	List<IProduct> find(ProductFilter filter);

	long getCount(ProductFilter filter);

}
