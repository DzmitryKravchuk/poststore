package com.itacademy.jd2.dk.poststore.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.dk.poststore.dao.api.IParcelZoneDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IParcelZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ParcelZoneFilter;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.ParcelZone;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.ParcelZone_;

@Repository
public class ParcelZoneDaoImpl extends AbstractDaoImpl<IParcelZone, Integer> implements IParcelZoneDao {

	protected ParcelZoneDaoImpl() {
		super(ParcelZone.class);
	}

	@Override
	public IParcelZone createEntity() {
		return new ParcelZone();
	}

	@Override
	public void save(IParcelZone... entities) {
		throw new RuntimeException("unsupported method");

	}

	@Override
	public List<IParcelZone> find(ParcelZoneFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IParcelZone> cq = cb.createQuery(IParcelZone.class); // define
		// type
		// of
		// result
		final Root<ParcelZone> from = cq.from(ParcelZone.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super ParcelZone, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<IParcelZone> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super ParcelZone, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return ParcelZone_.created;
		case "updated":
			return ParcelZone_.updated;
		case "id":
			return ParcelZone_.id;
		case "price4g100":
			return ParcelZone_.price4g100;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(ParcelZoneFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<ParcelZone> from = cq.from(ParcelZone.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
