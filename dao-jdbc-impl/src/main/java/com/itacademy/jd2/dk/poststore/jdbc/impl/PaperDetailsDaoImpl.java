package com.itacademy.jd2.dk.poststore.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.dk.poststore.dao.api.IPaperDetailsDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;
import com.itacademy.jd2.dk.poststore.dao.api.filter.PaperDetailsFilter;
import com.itacademy.jd2.dk.poststore.jdbc.impl.entity.PaperDetails;
import com.itacademy.jd2.dk.poststore.jdbc.impl.util.SQLExecutionException;

@Repository
public class PaperDetailsDaoImpl extends AbstractDaoImpl<IPaperDetails, Integer> implements IPaperDetailsDao {

	@Override
	public IPaperDetails createEntity() {
		return new PaperDetails();
	}

	@Override
	protected IPaperDetails parseRow(final ResultSet resultSet) throws SQLException {
		final IPaperDetails entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
//		entity.setBrand(resultSet.getString("brand"));
//		entity.setDetails(resultSet.getString("details"));
		entity.setPrice4Paper(resultSet.getDouble("price_4_A4"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		return entity;
	}

	@Override
	public void update(IPaperDetails entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"update %s set updated=?, brand=?, details=?, price_4_A4=? where id=?", getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setObject(1, entity.getUpdated(), Types.TIMESTAMP);
//				pStmt.setString(2, entity.getBrand());
//				pStmt.setString(3, entity.getDetails());
				pStmt.setDouble(4, entity.getPrice4Paper());
				pStmt.setInt(5, entity.getId());
				pStmt.executeUpdate();
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
	public void insert(IPaperDetails entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("insert into %s (brand, details, price_4_A4, created, updated) values(?,?,?,?,?)",
								getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
//				pStmt.setString(1, entity.getBrand());
//				pStmt.setString(2, entity.getDetails());
				pStmt.setDouble(3, entity.getPrice4Paper());
				pStmt.setObject(4, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();

				entity.setId(id);
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
		return "paper";
	}

	@Override
	public List<IPaperDetails> find(PaperDetailsFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendJOINs(filter, sql);
		appendWHEREs(filter, sql);
		appendSort(filter, sql);
		appendPaging(filter, sql);
		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(PaperDetailsFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendJOINs(filter, sql);
		appendWHEREs(filter, sql);
		return executeCountQuery(sql.toString());

	}

	private void appendJOINs(final PaperDetailsFilter filter, final StringBuilder sb) {
		// nothing yet
	}

	private void appendWHEREs(final PaperDetailsFilter filter, final StringBuilder sb) {
		// nothing yet
	}

}
