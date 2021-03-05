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

import com.itacademy.jd2.dk.poststore.dao.api.IProductDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ProductFilter;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.Product;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.Product_;

@Repository
public class ProductDaoImpl extends AbstractDaoImpl<IProduct, Integer> implements IProductDao {

	protected ProductDaoImpl() {
		super(Product.class);
	}

	@Override
	public IProduct createEntity() {
		final Product product = new Product();
		return product;
	}

	@Override
	public void save(IProduct... entities) {
		throw new RuntimeException("unsupported method");

	}

	@Override
	public List<IProduct> find(ProductFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IProduct> cq = cb.createQuery(IProduct.class); // define
		// type
		// of
		// result
		final Root<Product> from = cq.from(Product.class);// select from *
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Product, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<IProduct> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Product, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return Product_.created;
		case "updated":
			return Product_.updated;
		case "id":
			return Product_.id;
		case "name":
			return Product_.name;
		case "price":
			return Product_.price;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(ProductFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<Product> from = cq.from(Product.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
