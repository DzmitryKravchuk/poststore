package com.itacademy.jd2.dk.poststore.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.dk.poststore.dao.api.IProductDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ProductFilter;
import com.itacademy.jd2.dk.poststore.jdbc.impl.entity.Product;
import com.itacademy.jd2.dk.poststore.jdbc.impl.util.PreparedStatementAction;
import com.itacademy.jd2.dk.poststore.jdbc.impl.util.SQLExecutionException;

@Repository
public class ProductDaoImpl extends AbstractDaoImpl<IProduct, Integer> implements IProductDao {

	@Override
	public IProduct createEntity() {
		return new Product();
	}

	@Override
	public void insert(final IProduct entity) {
		executeStatement(new PreparedStatementAction<IProduct>(
				String.format("insert into %s (name, price,created, updated) values(?,?,?,?)", getTableName()), true) {
			@Override
			public IProduct doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setDouble(2, entity.getPrice());
				pStmt.setObject(3, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();

				entity.setId(id);
				return entity;
			}
		});

	}

	@Override
	public void update(final IProduct entity) {
		executeStatement(new PreparedStatementAction<IProduct>(
				String.format("update %s set name=?,price=?, updated=? where id=?", getTableName())) {
			@Override
			public IProduct doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setDouble(2, entity.getPrice());
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected String getTableName() {
		return "product";
	}

	@Override
	protected IProduct parseRow(final ResultSet resultSet) throws SQLException {
		final IProduct entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setPrice(resultSet.getDouble("price"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public void save(IProduct... entities) {
		try (Connection c = getConnection()) {
			c.setAutoCommit(false);
			try {

				for (IProduct entity : entities) {
					PreparedStatement pStmt = c.prepareStatement(String
							.format("insert into %s (name, price,created, updated) values(?,?,?,?)", getTableName()),
							Statement.RETURN_GENERATED_KEYS);

					pStmt.setString(1, entity.getName());
					pStmt.setDouble(2, entity.getPrice());
					pStmt.setObject(3, entity.getCreated(), Types.TIMESTAMP);
					pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);

					pStmt.executeUpdate();

					final ResultSet rs = pStmt.getGeneratedKeys();
					rs.next();
					final int id = rs.getInt("id");

					rs.close();
					pStmt.close();
					entity.setId(id);
				}

				// the same should be done in 'update' DAO method
				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	public List<IProduct> find(ProductFilter filter) {
		 final StringBuilder sqlTile = new StringBuilder("");
	        appendSort(filter, sqlTile);
	        appendPaging(filter, sqlTile);
	        return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(ProductFilter filter) {
		return executeCountQuery("");
	}

}
