package com.itacademy.jd2.dk.poststore.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.dk.poststore.dao.api.IUserAccountDetailsDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccountDetails;
import com.itacademy.jd2.dk.poststore.jdbc.impl.entity.CustomerDetails;
import com.itacademy.jd2.dk.poststore.jdbc.impl.util.SQLExecutionException;

@Repository
public class CustomerDetailsDaoImpl extends AbstractDaoImpl<IUserAccountDetails, Integer> implements IUserAccountDetailsDao {

	@Override
	public IUserAccountDetails createEntity() {
		return new CustomerDetails();
	}

	@Override
	protected IUserAccountDetails parseRow(final ResultSet resultSet) throws SQLException {
		final IUserAccountDetails entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setAdress(resultSet.getString("adress"));
		entity.setPhone(resultSet.getInt("phone"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		return entity;
	}

	@Override
	public void update(IUserAccountDetails entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String
						.format("update %s set updated=?, name=?, adress=?, phone=? where id=?", getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setObject(1, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setString(2, entity.getName());
				pStmt.setString(3, entity.getAdress());
				pStmt.setInt(4, entity.getPhone());
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
	public void insert(IUserAccountDetails entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("insert into %s (name, adress, phone, id, created, updated) values(?,?,?,?,?,?)",
								getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getAdress());
				pStmt.setInt(3, entity.getPhone());
				pStmt.setInt(4, entity.getUserAccount().getId());
				pStmt.setObject(5, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(6, entity.getUpdated(), Types.TIMESTAMP);

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
		return "customer_details";
	}

}
