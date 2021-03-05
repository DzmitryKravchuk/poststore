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

import com.itacademy.jd2.dk.poststore.dao.api.IUserAccountDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.UserAccount;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.UserAccount_;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	protected UserAccountDaoImpl() {
		super(UserAccount.class);
	}

	@Override
	public IUserAccount createEntity() {
		final UserAccount userAccount = new UserAccount();
		return userAccount;
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class); // define
		// type
		// of
		// result
		final Root<UserAccount> from = cq.from(UserAccount.class);// select from *
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super UserAccount, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<IUserAccount> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super UserAccount, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return UserAccount_.created;
		case "updated":
			return UserAccount_.updated;
		case "id":
			return UserAccount_.id;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<UserAccount> from = cq.from(UserAccount.class); // define table for select

		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public void save(IUserAccount[] entities) {
		throw new RuntimeException("unsupported method");

	}

	@Override
	public IUserAccount getUserByLogin(final String username) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class); // define returning result
		final Root<UserAccount> from = cq.from(UserAccount.class); // define table for select

		cq.select(from); // define what need to be selected

		// cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(UserAccount_.eMail), username));

		final TypedQuery<IUserAccount> q = em.createQuery(cq);

		return getSingleResult(q);
	}
}
