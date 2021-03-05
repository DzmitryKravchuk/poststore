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

import com.itacademy.jd2.dk.poststore.dao.api.IInventoryDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IInventory;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.dao.api.filter.InventoryFilter;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.Inventory;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.Inventory_;

@Repository
public class InventoryDaoImpl extends AbstractDaoImpl<IInventory, Integer> implements IInventoryDao {

	protected InventoryDaoImpl() {
		super(Inventory.class);
	}

	@Override
	public IInventory createEntity() {
		return new Inventory();
	}

	@Override
	public List<IInventory> find(InventoryFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IInventory> cq = cb.createQuery(IInventory.class); // define
																				// type
																				// of
		// result
		final Root<Inventory> from = cq.from(Inventory.class);// select from *
		cq.select(from).distinct(true); // select what? select *

		if (filter.isFetchStore()) {
			from.fetch(Inventory_.store, JoinType.LEFT);
		}
		if (filter.isFetchProduct()) {
			from.fetch(Inventory_.product, JoinType.LEFT);
		}
		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Inventory, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<IInventory> q = em.createQuery(cq);

		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Inventory, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return Inventory_.created;
		case "updated":
			return Inventory_.updated;
		case "id":
			return Inventory_.id;
		case "product":
			return Inventory_.product;
		case "store":
			return Inventory_.store;
		case "quantity":
			return Inventory_.quantity;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(InventoryFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<Inventory> from = cq.from(Inventory.class); // select from *
		cq.select(cb.count(from)); // select what? select count(*)

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public void save(IInventory[] entities) {
		throw new RuntimeException("unsupported method");
	}

	@Override
	public IInventory getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IInventory> cq = cb.createQuery(IInventory.class); // define returning result
		final Root<Inventory> from = cq.from(Inventory.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Inventory_.product, JoinType.LEFT);
		from.fetch(Inventory_.store, JoinType.LEFT);

		// .. where id=...
		cq.where(cb.equal(from.get(Inventory_.id), id)); // where id=?

		final TypedQuery<IInventory> q = em.createQuery(cq);

		return getSingleResult(q);
	}
	@Override
	public IInventory getInventoryItem(IStore store, IProduct product) {
		IInventory inventoryItem = new Inventory();

		try {
			final EntityManager em = getEntityManager();
			final CriteriaBuilder cb = em.getCriteriaBuilder();

			final CriteriaQuery<IInventory> cq = cb.createQuery(IInventory.class); // define returning result
			final Root<Inventory> from = cq.from(Inventory.class); // define table for select

			cq.select(from); // define what need to be selected

			Predicate criteria = cb.conjunction();
			Predicate p1 = cb.equal(from.get(Inventory_.store), store.getId());
			criteria = cb.and(criteria, p1);
			Predicate p2 = cb.equal(from.get(Inventory_.product), product.getId());
			criteria = cb.and(criteria, p2);

			cq.where(criteria);

			final TypedQuery<IInventory> q = em.createQuery(cq);
			inventoryItem = getSingleResult(q);
		} catch (IllegalArgumentException e) {
		}
		return inventoryItem;
	}
}
