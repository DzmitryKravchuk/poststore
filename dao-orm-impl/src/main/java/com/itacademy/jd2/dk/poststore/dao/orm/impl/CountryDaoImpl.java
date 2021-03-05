package com.itacademy.jd2.dk.poststore.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.dk.poststore.dao.api.ICountryDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICountry;
import com.itacademy.jd2.dk.poststore.dao.api.filter.CountryFilter;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.Country;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.Country_;

@Repository
public class CountryDaoImpl extends AbstractDaoImpl<ICountry, Integer> implements ICountryDao {

	protected CountryDaoImpl() {
		super(Country.class);
	}

	@Override
	public ICountry createEntity() {
		final Country country = new Country();
		return country;
	}

	@Override
	public ICountry getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ICountry> cq = cb.createQuery(ICountry.class); // define returning result
		final Root<Country> from = cq.from(Country.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Country_.expressZone, JoinType.LEFT);
		from.fetch(Country_.letterZone, JoinType.LEFT);
		from.fetch(Country_.parcelZone, JoinType.LEFT);

		// .. where id=...
		cq.where(cb.equal(from.get(Country_.id), id)); // where id=?

		final TypedQuery<ICountry> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public void save(ICountry... entities) {
		throw new RuntimeException("unsupported method");

	}

	@Override
	public List<ICountry> find(CountryFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ICountry> cq = cb.createQuery(ICountry.class); // define
																			// type
																			// of
		// result
		final Root<Country> from = cq.from(Country.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.isFetchExpressZone()) {
			from.fetch(Country_.expressZone, JoinType.LEFT);
		}
		if (filter.isFetchLetterZone()) {
			from.fetch(Country_.letterZone, JoinType.LEFT);
		}
		if (filter.isFetchParcelZone()) {
			from.fetch(Country_.parcelZone, JoinType.LEFT);
		}
		if (filter.getSortColumn() != null)

		{
			final SingularAttribute<? super Country, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<ICountry> q = em.createQuery(cq);

		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Country, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return Country_.created;
		case "updated":
			return Country_.updated;
		case "id":
			return Country_.id;
		case "name":
			return Country_.name;

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(CountryFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<Country> from = cq.from(Country.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
