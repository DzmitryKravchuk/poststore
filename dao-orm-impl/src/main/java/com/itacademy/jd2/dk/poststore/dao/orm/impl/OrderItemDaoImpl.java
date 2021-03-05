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

import com.itacademy.jd2.dk.poststore.dao.api.IOrderItemDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderItem;
import com.itacademy.jd2.dk.poststore.dao.api.filter.OrderItemFilter;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.OrderItem;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.OrderItem_;

@Repository
public class OrderItemDaoImpl extends AbstractDaoImpl<IOrderItem, Integer> implements IOrderItemDao {

	protected OrderItemDaoImpl() {
		super(OrderItem.class);
	}

	@Override
	public IOrderItem createEntity() {
		return new OrderItem();
	}

	@Override
	public List<IOrderItem> find(OrderItemFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IOrderItem> cq = cb.createQuery(IOrderItem.class); // define
		// type
		// of
		// result
		final Root<OrderItem> from = cq.from(OrderItem.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.isFetchProduct()) {
			from.fetch(OrderItem_.product, JoinType.LEFT);
		}
		if (filter.isFetchOrderProduct()) {
			from.fetch(OrderItem_.orderProduct, JoinType.LEFT);
		}
		// orderProductId
		if (filter.getOrderProductId() != null) {
			cq.where(cb.equal(from.get(OrderItem_.orderProduct), filter.getOrderProductId())); // where id=?
		}

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super OrderItem, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<IOrderItem> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super OrderItem, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return OrderItem_.created;
		case "updated":
			return OrderItem_.updated;
		case "id":
			return OrderItem_.id;
		case "quantity":
			return OrderItem_.quantity;
		case "product":
			return OrderItem_.product;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(OrderItemFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<OrderItem> from = cq.from(OrderItem.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)

		// orderProductId
		if (filter.getOrderProductId() != null) {
			cq.where(cb.equal(from.get(OrderItem_.orderProduct), filter.getOrderProductId())); // where id=?
		}

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public void save(IOrderItem[] entities) {
		throw new RuntimeException("unsupported method");
	}

	@Override
	public IOrderItem getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IOrderItem> cq = cb.createQuery(IOrderItem.class); // define returning result
		final Root<OrderItem> from = cq.from(OrderItem.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(OrderItem_.orderProduct, JoinType.LEFT);
		from.fetch(OrderItem_.product, JoinType.LEFT);

		// .. where id=...
		cq.where(cb.equal(from.get(OrderItem_.id), id)); // where id=?

		final TypedQuery<IOrderItem> q = em.createQuery(cq);

		return getSingleResult(q);
	}

}
