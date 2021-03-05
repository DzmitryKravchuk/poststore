package com.itacademy.jd2.dk.poststore.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.dk.poststore.dao.api.IExpressZoneDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ExpressZoneFilter;
import com.itacademy.jd2.dk.poststore.jdbc.impl.entity.ExpressZone;
import com.itacademy.jd2.dk.poststore.jdbc.impl.util.SQLExecutionException;

@Repository
public class ExpressZoneDaoImpl extends AbstractDaoImpl<IExpressZone, Integer> implements IExpressZoneDao {

	@Override
	public IExpressZone createEntity() {
		return new ExpressZone();
	}

	@Override
	public void insert(final IExpressZone entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("insert into %s (price_4_100g, created, updated) values(?,?,?)", getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			//c.setAutoCommit(false);
		//	try {
				pStmt.setDouble(1, entity.getPrice4g100());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();
				entity.setId(id);
				// the same should be done in 'update' DAO method

			//} catch (final Exception e) {
			//	c.rollback();
			//	throw new RuntimeException(e);
			//}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	public void update(final IExpressZone entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("update %s set price_4_100g=?, updated=? where id=?", getTableName()))) {
			//c.setAutoCommit(false);
			//try {
				pStmt.setDouble(1, entity.getPrice4g100());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getId());
				pStmt.executeUpdate();
				// the same should be done in 'update' DAO method

			//} catch (final Exception e) {
			//	c.rollback();
			//	throw new RuntimeException(e);
			//}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	protected IExpressZone parseRow(final ResultSet resultSet) throws SQLException {
		final IExpressZone entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setPrice4g100(resultSet.getDouble("price_4_100g"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		return entity;
	}

	@Override
	public void deleteAll() {
		try (Connection c = getConnection();
				PreparedStatement deleteAllStmt = c.prepareStatement("delete from " + getTableName());) {
			c.setAutoCommit(false);
			try {
				deleteAllStmt.executeUpdate();

				deleteAllStmt.close();

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
	protected String getTableName() {
		return "express_zone";
	}

	@Override
	public List<IExpressZone> find(ExpressZoneFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(final ExpressZoneFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public void save(IExpressZone... entities) {
		try (Connection c = getConnection()) {
			c.setAutoCommit(false);
			try {

				for (IExpressZone entity : entities) {
					PreparedStatement pStmt = c.prepareStatement(String
							.format("insert into %s (price_4_100g, created, updated) values(?,?,?)", getTableName()),
							Statement.RETURN_GENERATED_KEYS);

					pStmt.setDouble(1, entity.getPrice4g100());
					pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
					pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);

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

}
