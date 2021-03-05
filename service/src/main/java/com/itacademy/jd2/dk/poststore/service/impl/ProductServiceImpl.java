package com.itacademy.jd2.dk.poststore.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.dk.poststore.dao.api.IProductDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ProductFilter;
import com.itacademy.jd2.dk.poststore.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	private IProductDao dao;

	@Autowired
	public ProductServiceImpl(IProductDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IProduct createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IProduct entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new product created: {}", entity);
		} else {
			LOGGER.debug("product updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IProduct get(final Integer id) {
		final IProduct entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all products");
		dao.deleteAll();
	}

	@Override
	public List<IProduct> getAll() {
		final List<IProduct> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IProduct... entities) {
		Date modified = new Date();
		for (IProduct iBrand : entities) {

			iBrand.setUpdated(modified);
			iBrand.setCreated(modified);

		}

		dao.save(entities);
	}

	@Override
	public List<IProduct> find(ProductFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(ProductFilter filter) {
		return dao.getCount(filter);
	}
}
