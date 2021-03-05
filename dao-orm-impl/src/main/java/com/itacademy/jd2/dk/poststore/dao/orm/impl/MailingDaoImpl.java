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

import com.itacademy.jd2.dk.poststore.dao.api.IMailingDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMailing;
import com.itacademy.jd2.dk.poststore.dao.api.filter.MailingFilter;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.Mailing;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.Mailing_;

@Repository
public class MailingDaoImpl extends AbstractDaoImpl<IMailing, Integer> implements IMailingDao {

	protected MailingDaoImpl() {
		super(Mailing.class);
	}

	@Override
	public IMailing createEntity() {
		return new Mailing();
	}

	@Override
	public List<IMailing> find(MailingFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IMailing> cq = cb.createQuery(IMailing.class); // define
																			// type
																			// of
		// result
		final Root<Mailing> from = cq.from(Mailing.class);// select from *
		cq.select(from).distinct(true); // select what? select *

		if (filter.isFetchCountry()) {
			from.fetch(Mailing_.country, JoinType.LEFT);
		}
		if (filter.isFetchUserAccount()) {
			from.fetch(Mailing_.userAccount, JoinType.LEFT);
		}

		// UserAccountID
		Predicate criteria = cb.conjunction();
		if (filter.getUserAccountId() != null) {
			Predicate p = cb.equal(from.get(Mailing_.userAccount), filter.getUserAccountId());
			criteria = cb.and(criteria, p);
		}
		cq.where(criteria);

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Mailing, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<IMailing> q = em.createQuery(cq);

		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Mailing, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return Mailing_.created;
		case "updated":
			return Mailing_.updated;
		case "id":
			return Mailing_.id;
		case "address":
			return Mailing_.address;
		case "addressee":
			return Mailing_.addressee;
		case "price":
			return Mailing_.price;
		case "weight":
			return Mailing_.weight;
		case "mailingType":
			return Mailing_.mailingType;
		case "country":
			return Mailing_.country;


		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(MailingFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<Mailing> from = cq.from(Mailing.class);
		cq.select(cb.count(from)); // select what? select count(*)

		if (filter.getUserAccountId() != null) {
			cq.where(cb.equal(from.get(Mailing_.userAccount), filter.getUserAccountId())); // where id=?
		}

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public void save(IMailing[] entities) {
		throw new RuntimeException("unsupported method");

	}

	@Override
	public IMailing getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IMailing> cq = cb.createQuery(IMailing.class); // define returning result
		final Root<Mailing> from = cq.from(Mailing.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Mailing_.country, JoinType.LEFT);
		from.fetch(Mailing_.userAccount, JoinType.LEFT);

		// .. where id=...
		cq.where(cb.equal(from.get(Mailing_.id), id)); // where id=?

		final TypedQuery<IMailing> q = em.createQuery(cq);

		return getSingleResult(q);

	}

}
