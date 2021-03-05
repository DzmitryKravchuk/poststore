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

import com.itacademy.jd2.dk.poststore.dao.api.IExpressZoneDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ExpressZoneFilter;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.ExpressZone;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.ExpressZone_;

@Repository
public class ExpressZoneDaoImpl extends AbstractDaoImpl<IExpressZone, Integer> implements IExpressZoneDao {

	protected ExpressZoneDaoImpl() {
		super(ExpressZone.class);
	}

	@Override
	public IExpressZone createEntity() {
		return new ExpressZone();
	}

	@Override
	public void save(IExpressZone... entities) {
		throw new RuntimeException("unsupported method");

	}

	@Override
	public List<IExpressZone> find(ExpressZoneFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IExpressZone> cq = cb.createQuery(IExpressZone.class); // define
		// type
		// of
		// result
		final Root<ExpressZone> from = cq.from(ExpressZone.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super ExpressZone, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<IExpressZone> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super ExpressZone, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return ExpressZone_.created;
		case "updated":
			return ExpressZone_.updated;
		case "id":
			return ExpressZone_.id;
		case "price4g100":
			return ExpressZone_.price4g100;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(ExpressZoneFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<ExpressZone> from = cq.from(ExpressZone.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
