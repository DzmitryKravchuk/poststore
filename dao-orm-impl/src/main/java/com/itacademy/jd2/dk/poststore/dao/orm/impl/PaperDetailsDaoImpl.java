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

import com.itacademy.jd2.dk.poststore.dao.api.IPaperDetailsDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;
import com.itacademy.jd2.dk.poststore.dao.api.filter.PaperDetailsFilter;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.PaperDetails;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.PaperDetails_;

@Repository
public class PaperDetailsDaoImpl extends AbstractDaoImpl<IPaperDetails, Integer> implements IPaperDetailsDao {

	protected PaperDetailsDaoImpl() {
		super(PaperDetails.class);
	}

	@Override
	public IPaperDetails createEntity() {
		final PaperDetails paperDetails = new PaperDetails();
		return paperDetails;
	}

	@Override
	public List<IPaperDetails> find(PaperDetailsFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IPaperDetails> cq = cb.createQuery(IPaperDetails.class); // define
		// type
		// of
		// result
		final Root<PaperDetails> from = cq.from(PaperDetails.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super PaperDetails, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<IPaperDetails> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super PaperDetails, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return PaperDetails_.created;
		case "updated":
			return PaperDetails_.updated;
		case "id":
			return PaperDetails_.id;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(PaperDetailsFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<PaperDetails> from = cq.from(PaperDetails.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
