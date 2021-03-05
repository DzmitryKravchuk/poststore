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

import com.itacademy.jd2.dk.poststore.dao.api.IMoneyTransferDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMoneyTransfer;
import com.itacademy.jd2.dk.poststore.dao.api.filter.MoneyTransferFilter;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.Courier_;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.MoneyTransfer;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.MoneyTransfer_;

@Repository
public class MoneyTransferDaoImpl extends AbstractDaoImpl<IMoneyTransfer, Integer> implements IMoneyTransferDao {
	protected MoneyTransferDaoImpl() {
		super(MoneyTransfer.class);
	}

	@Override
	public IMoneyTransfer createEntity() {
		return new MoneyTransfer();
	}

	@Override
	public List<IMoneyTransfer> find(MoneyTransferFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IMoneyTransfer> cq = cb.createQuery(IMoneyTransfer.class); // define
		// type
		// of
		// result
		final Root<MoneyTransfer> from = cq.from(MoneyTransfer.class);// select from *
		cq.select(from); // select what? select *

		if (filter.isFetchUserAccount()) {
			from.fetch(MoneyTransfer_.userAccount, JoinType.LEFT);
		}

		// UserAccountID
		Predicate criteria = cb.conjunction();
		if (filter.getUserAccountId() != null) {
			Predicate p = cb.equal(from.get(MoneyTransfer_.userAccount), filter.getUserAccountId());
			criteria = cb.and(criteria, p);
		}
		cq.where(criteria);

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super MoneyTransfer, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<IMoneyTransfer> q = em.createQuery(cq);

		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super MoneyTransfer, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return MoneyTransfer_.created;
		case "updated":
			return MoneyTransfer_.updated;
		case "id":
			return MoneyTransfer_.id;
		case "amount":
			return MoneyTransfer_.amount;
		case "beneficiaryAdress":
			return MoneyTransfer_.beneficiaryAdress;
		case "beneficiaryName":
			return MoneyTransfer_.beneficiaryName;
		case "transactionPrice":
			return MoneyTransfer_.transactionPrice;
		case "payerAdress":
			return MoneyTransfer_.payerAdress;
		case "payerName":
			return MoneyTransfer_.payerName;

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(MoneyTransferFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<MoneyTransfer> from = cq.from(MoneyTransfer.class); // select from *
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public void save(IMoneyTransfer[] entities) {
		throw new RuntimeException("unsupported method");
	}

	@Override
	public IMoneyTransfer getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IMoneyTransfer> cq = cb.createQuery(IMoneyTransfer.class); // define returning result
		final Root<MoneyTransfer> from = cq.from(MoneyTransfer.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(MoneyTransfer_.userAccount, JoinType.LEFT);

		// .. where id=...
		cq.where(cb.equal(from.get(Courier_.id), id)); // where id=?

		final TypedQuery<IMoneyTransfer> q = em.createQuery(cq);

		return getSingleResult(q);
	}

}
