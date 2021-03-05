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

import com.itacademy.jd2.dk.poststore.dao.api.IPolygraphyDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPolygraphy;
import com.itacademy.jd2.dk.poststore.dao.api.filter.PolygraphyFilter;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.PaperDetails_;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.Polygraphy;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.Polygraphy_;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.UserAccount_;

@Repository
public class PolygraphyDaoImpl extends AbstractDaoImpl<IPolygraphy, Integer> implements IPolygraphyDao {

	protected PolygraphyDaoImpl() {
		super(Polygraphy.class);
	}

	@Override
	public IPolygraphy createEntity() {
		return new Polygraphy();
	}

	@Override
	public List<IPolygraphy> find(PolygraphyFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IPolygraphy> cq = cb.createQuery(IPolygraphy.class); // define
		// type
		// of
		// result
		final Root<Polygraphy> from = cq.from(Polygraphy.class);// select from *
		cq.select(from).distinct(true); // select what? select *

		if (filter.isFetchPaperDetails()) {
			from.fetch(Polygraphy_.paperDetails, JoinType.LEFT);
		}
		if (filter.isFetchUserAccount()) {
			from.fetch(Polygraphy_.userAccount, JoinType.LEFT);
		}

		// UserAccountID
		Predicate criteria = cb.conjunction();
		if (filter.getUserAccountId() != null) {
			Predicate p = cb.equal(from.get(Polygraphy_.userAccount), filter.getUserAccountId());
			criteria = cb.and(criteria, p);
		}
		cq.where(criteria);

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Polygraphy, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<IPolygraphy> q = em.createQuery(cq);

		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Polygraphy, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return Polygraphy_.created;
		case "updated":
			return Polygraphy_.updated;
		case "id":
			return Polygraphy_.id;
		case "paperDetails":
			return Polygraphy_.paperDetails;
		case "price":
			return Polygraphy_.price;

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(PolygraphyFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<Polygraphy> from = cq.from(Polygraphy.class); // select from *
		cq.select(cb.count(from)); // select what? select count(*)

		if (filter.getUserAccountId() != null) {
			cq.where(cb.equal(from.get(Polygraphy_.userAccount), filter.getUserAccountId())); // where id=?
		}

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public void save(IPolygraphy[] entities) {
		throw new RuntimeException("unsupported method");
	}

	@Override
	public IPolygraphy getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IPolygraphy> cq = cb.createQuery(IPolygraphy.class); // define returning result
		final Root<Polygraphy> from = cq.from(Polygraphy.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Polygraphy_.userAccount, JoinType.LEFT);
		from.fetch(Polygraphy_.paperDetails, JoinType.LEFT);

		// .. where id=...
		cq.where(cb.equal(from.get(UserAccount_.id), id)); // where id=?
		cq.where(cb.equal(from.get(PaperDetails_.id), id)); // where id=?

		final TypedQuery<IPolygraphy> q = em.createQuery(cq);

		return getSingleResult(q);
	}
}
