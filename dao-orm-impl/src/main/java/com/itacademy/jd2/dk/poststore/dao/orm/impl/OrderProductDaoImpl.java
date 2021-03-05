package com.itacademy.jd2.dk.poststore.dao.orm.impl;

import java.util.Calendar;
import java.util.Date;
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

import com.itacademy.jd2.dk.poststore.dao.api.IOrderProductDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.Status;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.filter.OrderProductFilter;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.OrderProduct;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.OrderProduct_;

@Repository
public class OrderProductDaoImpl extends AbstractDaoImpl<IOrderProduct, Integer> implements IOrderProductDao {

	protected OrderProductDaoImpl() {
		super(OrderProduct.class);
	}

	@Override
	public IOrderProduct createEntity() {
		final OrderProduct orderProduct = new OrderProduct();
		return orderProduct;
	}

	@Override
	public List<IOrderProduct> find(OrderProductFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IOrderProduct> cq = cb.createQuery(IOrderProduct.class); // define
		// type
		// of
		// result
		final Root<OrderProduct> from = cq.from(OrderProduct.class);// select from *
		cq.select(from); // select what? select *

		if (filter.isFetchUserAccount()) {
			from.fetch(OrderProduct_.userAccount, JoinType.LEFT);
		}

		// UserAccountID
		if (filter.getUserAccountId() != null) {
			cq.where(cb.equal(from.get(OrderProduct_.userAccount), filter.getUserAccountId()));
		}

		// OrderStatus
		if (filter.getStatus() != null) {
			cq.where(cb.equal(from.get(OrderProduct_.status), filter.getStatus()));
		}

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super OrderProduct, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<IOrderProduct> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super OrderProduct, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return OrderProduct_.created;
		case "updated":
			return OrderProduct_.updated;
		case "id":
			return OrderProduct_.id;
		case "status":
			return OrderProduct_.status;
		case "cost":
			return OrderProduct_.cost;
			
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(OrderProductFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<OrderProduct> from = cq.from(OrderProduct.class); // select from *
		cq.select(cb.count(from)); // select what? select count(*)

		// UserAccountID
		if (filter.getUserAccountId() != null) {
			cq.where(cb.equal(from.get(OrderProduct_.userAccount), filter.getUserAccountId()));
		}

		// OrderStatus
		if (filter.getStatus() != null) {
			cq.where(cb.equal(from.get(OrderProduct_.status), filter.getStatus()));
		}

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public void save(IOrderProduct[] entities) {
		throw new RuntimeException("unsupported method");
	}

	@Override
	public IOrderProduct getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IOrderProduct> cq = cb.createQuery(IOrderProduct.class); // define returning result
		final Root<OrderProduct> from = cq.from(OrderProduct.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(OrderProduct_.userAccount, JoinType.LEFT);

		// .. where id=...
		cq.where(cb.equal(from.get(OrderProduct_.id), id)); // where id=?

		final TypedQuery<IOrderProduct> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public IOrderProduct getProductCart(IUserAccount userAccount) {
		IOrderProduct productCart = new OrderProduct();

		try {
			final EntityManager em = getEntityManager();
			final CriteriaBuilder cb = em.getCriteriaBuilder();

			final CriteriaQuery<IOrderProduct> cq = cb.createQuery(IOrderProduct.class); // define returning result
			final Root<OrderProduct> from = cq.from(OrderProduct.class); // define table for select

			cq.select(from); // define what need to be selected

			Predicate criteria = cb.conjunction();
			Predicate p1 = cb.equal(from.get(OrderProduct_.userAccount), userAccount.getId());
			criteria = cb.and(criteria, p1);
			Predicate p2 = cb.equal(from.get(OrderProduct_.status), Status.productCart);
			criteria = cb.and(criteria, p2);

			cq.where(criteria);

			final TypedQuery<IOrderProduct> q = em.createQuery(cq);
			productCart = getSingleResult(q);
		} catch (IllegalArgumentException e) {
		}
		return productCart;
	}

	@Override
	public List<IOrderProduct> getPreviousMonthOrders() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		Date firstDateOfCurrentMonth = cal.getTime();

		cal.add(Calendar.MONTH, -2);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		Date lastDateOfTheMonthBefore = cal.getTime();

		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IOrderProduct> cq = cb.createQuery(IOrderProduct.class);
		final Root<OrderProduct> from = cq.from(OrderProduct.class);
		cq.select(from);

		Predicate p1 = cb.lessThan(from.get(OrderProduct_.updated), firstDateOfCurrentMonth);
		Predicate p2 = cb.greaterThan(from.get(OrderProduct_.updated), lastDateOfTheMonthBefore);
		Predicate p3 = cb.equal(from.get(OrderProduct_.status), Status.executed);
		cq.where(cb.and(p3, p2, p1));

		final TypedQuery<IOrderProduct> q = em.createQuery(cq);
		List<IOrderProduct> list = q.getResultList();
		return list;

	}
}
