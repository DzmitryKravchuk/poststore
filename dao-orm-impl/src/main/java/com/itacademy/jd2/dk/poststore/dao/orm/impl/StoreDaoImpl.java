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

import com.itacademy.jd2.dk.poststore.dao.api.IStoreDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.StoreType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.dao.api.filter.StoreFilter;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.Store;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.Store_;

@Repository
public class StoreDaoImpl extends AbstractDaoImpl<IStore, Integer> implements IStoreDao {

	protected StoreDaoImpl() {
		super(Store.class);
	}

	@Override
	public IStore createEntity() {
		return new Store();
	}

	@Override
	public List<IStore> find(StoreFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IStore> cq = cb.createQuery(IStore.class); // define
																		// type
																		// of
		// result
		final Root<Store> from = cq.from(Store.class);// select from *
		cq.select(from).distinct(true); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Store, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<IStore> q = em.createQuery(cq);

		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Store, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return Store_.created;
		case "updated":
			return Store_.updated;
		case "id":
			return Store_.id;
		case "product":
			return Store_.storeType;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(StoreFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<Store> from = cq.from(Store.class); // select from *
		cq.select(cb.count(from)); // select what? select count(*)

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public void save(IStore[] entities) {
		throw new RuntimeException("unsupported method");
	}

	@Override
	public IStore getStoreByType(StoreType storeType) {
		IStore store = new Store();

		try {
			final EntityManager em = getEntityManager();
			final CriteriaBuilder cb = em.getCriteriaBuilder();

			final CriteriaQuery<IStore> cq = cb.createQuery(IStore.class); // define returning result
			final Root<Store> from = cq.from(Store.class); // define table for select

			cq.select(from); // define what need to be selected

			cq.where(cb.equal(from.get(Store_.storeType), storeType)); // where storeType=?

			final TypedQuery<IStore> q = em.createQuery(cq);
			store = getSingleResult(q);
		} catch (IllegalArgumentException e) {
		}
		return store;
	}
}
