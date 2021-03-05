package com.itacademy.jd2.dk.poststore.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.dk.poststore.dao.api.IUserAccountDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.dk.poststore.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.dk.poststore.jdbc.impl.util.SQLExecutionException;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	@Override
	public IUserAccount createEntity() {
		return new UserAccount();
	}

	@Override
	protected IUserAccount parseRow(final ResultSet resultSet) throws SQLException {
		final IUserAccount entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.seteMail(resultSet.getString("e_mail"));
		entity.setPassword(resultSet.getString("password"));
		entity.setUserRole(UserRole.valueOf(resultSet.getString("role_id")));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		return entity;
	}

	@Override
	public void update(IUserAccount entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"update %s set updated=?, e_mail=?, password=?, role_id=? where id=?", getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setObject(1, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setString(2, entity.geteMail());
				pStmt.setString(3, entity.getPassword());
				pStmt.setString(4, entity.getUserRole().name());
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
	public void insert(IUserAccount entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("insert into %s (e_mail, password, role_id, created, updated) values(?,?,?,?,?)",
								getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.geteMail());
				pStmt.setString(2, entity.getPassword());
				pStmt.setString(3, entity.getUserRole().name());
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
		return "user_account";
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendJOINs(filter, sql);
		appendWHEREs(filter, sql);
		appendSort(filter, sql);
		appendPaging(filter, sql);
		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendJOINs(filter, sql);
		appendWHEREs(filter, sql);
		return executeCountQuery(sql.toString());
	}

	private void appendJOINs(final UserAccountFilter filter, final StringBuilder sb) {
		// nothing yet
	}

	private void appendWHEREs(final UserAccountFilter filter, final StringBuilder sb) {
		// nothing yet
	}

	@Override
	public void save(IUserAccount[] entities) {
		try (Connection c = getConnection()) {
			c.setAutoCommit(false);
			try {

				for (IUserAccount entity : entities) {
					PreparedStatement pStmt = c.prepareStatement(String.format(
							"insert into %s (created, updated, e_mail, password, role_id) values(?,?,?,?,?)",
							getTableName()), Statement.RETURN_GENERATED_KEYS);

					pStmt.setObject(1, entity.getCreated(), Types.TIMESTAMP);
					pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
					pStmt.setString(3, entity.geteMail());
					pStmt.setString(4, entity.getPassword());
					pStmt.setString(5, entity.getUserRole().name());

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
	public IUserAccount getUserByLogin(String username) {

		return null;
	}

}
