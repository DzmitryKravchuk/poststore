package com.itacademy.jd2.dk.poststore.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.dk.poststore.dao.api.ICourierDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICourier;
import com.itacademy.jd2.dk.poststore.dao.api.filter.CourierFilter;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.Courier;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.Courier_;

@Repository
public class CourierDaoImpl extends AbstractDaoImpl<ICourier, Integer> implements ICourierDao {

	protected CourierDaoImpl() {
		super(Courier.class);
	}

	@Override
	public ICourier createEntity() {
		final Courier courier = new Courier();
		return courier;
	}

	@Override
	public long getCount(CourierFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<Courier> from = cq.from(Courier.class); // select from *
		cq.select(cb.count(from)); // select what? select count(*)

		if (filter.getUserAccountId() != null) {
			cq.where(cb.equal(from.get(Courier_.userAccount), filter.getUserAccountId())); // where id=?
		}

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public void save(ICourier[] entities) {
		throw new RuntimeException("unsupported method");
	}

	@Override
	public List<ICourier> find(CourierFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ICourier> cq = cb.createQuery(ICourier.class); // define
																			// type
																			// of
		// result
		final Root<Courier> from = cq.from(Courier.class);// select from *
		cq.select(from); // select what? select *

		if (filter.isFetchUserAccount()) {
			from.fetch(Courier_.userAccount, JoinType.LEFT);
		}

		// UserAccountID
		Predicate criteria = cb.conjunction();
		if (filter.getUserAccountId() != null) {
			Predicate p = cb.equal(from.get(Courier_.userAccount), filter.getUserAccountId());
			criteria = cb.and(criteria, p);
		}
		cq.where(criteria);

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Courier, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<ICourier> q = em.createQuery(cq);

		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Courier, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return Courier_.created;
		case "updated":
			return Courier_.updated;
		case "id":
			return Courier_.id;
		case "shippingFrom":
			return Courier_.shippingFrom;
		case "shippingTo":
			return Courier_.shippingTo;
		case "price":
			return Courier_.price;
		case "addressee":
			return Courier_.addressee;

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public ICourier getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ICourier> cq = cb.createQuery(ICourier.class); // define returning result
		final Root<Courier> from = cq.from(Courier.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Courier_.userAccount, JoinType.LEFT);

		// .. where id=...
		cq.where(cb.equal(from.get(Courier_.id), id)); // where id=?

		final TypedQuery<ICourier> q = em.createQuery(cq);

		return getSingleResult(q);

	}

}
