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

import com.itacademy.jd2.dk.poststore.dao.api.ILetterZoneDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ILetterZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.LetterZoneFilter;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.LetterZone;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.LetterZone_;

@Repository
public class LetterZoneDaoImpl extends AbstractDaoImpl<ILetterZone, Integer> implements ILetterZoneDao {

	protected LetterZoneDaoImpl() {
		super(LetterZone.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ILetterZone createEntity() {
		return new LetterZone();
	}

	@Override
	public void save(ILetterZone... entities) {
		throw new RuntimeException("unsupported method");

	}

	@Override
	public List<ILetterZone> find(LetterZoneFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ILetterZone> cq = cb.createQuery(ILetterZone.class); // define
		// type
		// of
		// result
		final Root<LetterZone> from = cq.from(LetterZone.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super LetterZone, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<ILetterZone> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super LetterZone, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return LetterZone_.created;
		case "updated":
			return LetterZone_.updated;
		case "id":
			return LetterZone_.id;
		case "price4g100":
			return LetterZone_.price4g100;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(LetterZoneFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<LetterZone> from = cq.from(LetterZone.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
