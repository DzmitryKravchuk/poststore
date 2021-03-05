package com.itacademy.jd2.dk.poststore.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.dk.poststore.dao.api.IPolygraphyDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.ImageFormat;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPolygraphy;
import com.itacademy.jd2.dk.poststore.dao.api.filter.PolygraphyFilter;
import com.itacademy.jd2.dk.poststore.service.IPolygraphyService;

@Service
public class PolygraphyServiceImpl implements IPolygraphyService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PolygraphyServiceImpl.class);

	private IPolygraphyDao dao;

	@Autowired
	public PolygraphyServiceImpl(IPolygraphyDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IPolygraphy get(Integer id) {
		final IPolygraphy entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IPolygraphy> getAll() {
		final List<IPolygraphy> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IPolygraphy entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			entity.setPrice(getActualPrice(entity));
			dao.insert(entity);
			LOGGER.info("new polygraphy created: {}", entity);
		} else {
			LOGGER.debug("polygraphy updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public void save(IPolygraphy... entities) {
		Date modified = new Date();
		for (IPolygraphy iPolygraphy : entities) {

			iPolygraphy.setUpdated(modified);
			iPolygraphy.setCreated(modified);
		}
		dao.save(entities);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);

	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all polygraphies");
		dao.deleteAll();
	}

	@Override
	public IPolygraphy createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IPolygraphy> find(PolygraphyFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(PolygraphyFilter filter) {
		return dao.getCount(filter);
	}

	private double getActualPrice(IPolygraphy entity) {
		Double formatFactor = 1.0;
		Double colourFactor = 1.0;
		Double duplexFactor = 1.0;

		if (entity.getFormat() == ImageFormat.A4) {
			formatFactor = 1.0;
		} else if (entity.getFormat() == ImageFormat.A3) {
			formatFactor = 2.0;
		} else if (entity.getFormat() == ImageFormat.A5) {
			formatFactor = 0.5;
		} else if (entity.getFormat() == ImageFormat.A6) {
			formatFactor = 0.25;
		}

		if (entity.getIsColoured() == true) {
			colourFactor = 1.5;
		}

		if (entity.getIsDuplexPrinting() == true) {
			duplexFactor = 1.5;
		}

		double actualPrice = Math.round((entity.getCopyCount() * colourFactor * duplexFactor
				* entity.getPaperDetails().getPrice4Paper() * formatFactor) * 100.0) / 100.0;
		return actualPrice;
	}

	@Override
	public IPolygraphy getFullInfo(Integer id) {
		final IPolygraphy entity = dao.getFullInfo(id);
		return entity;
	}
}
