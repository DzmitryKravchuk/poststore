package com.itacademy.jd2.dk.poststore.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.dk.poststore.dao.api.IStoreDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.StoreType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.filter.StoreFilter;
import com.itacademy.jd2.dk.poststore.jdbc.impl.entity.Store;
import com.itacademy.jd2.dk.poststore.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.dk.poststore.jdbc.impl.util.SQLExecutionException;

@Repository
public class StoreDaoImpl extends AbstractDaoImpl<IStore, Integer> implements IStoreDao {

	@Override
	public IStore createEntity() {
		return new Store();
	}

	@Override
	protected IStore parseRow(final ResultSet resultSet) throws SQLException {
		final IStore entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
//		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final IUserAccount userAccount = new UserAccount();
		userAccount.setId((Integer) resultSet.getObject("manager_id"));
//		entity.setUserAccount(userAccount);
		return entity;
	}

	@Override
	public void update(IStore entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("update %s set updated=?, name=?, manager_id=? where id=?", getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setObject(1, entity.getUpdated(), Types.TIMESTAMP);
//				pStmt.setString(2, entity.getName());
	//			pStmt.setInt(3, entity.getUserAccount().getId());
				pStmt.setInt(4, entity.getId());
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
	public void insert(IStore entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String
						.format("insert into %s (name, manager_id, created, updated) values(?,?,?,?)", getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
	//			pStmt.setString(1, entity.getName());
	//			pStmt.setInt(2, entity.getUserAccount().getId());
				pStmt.setObject(3, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);

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
	public List<IStore> find(StoreFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendJOINs(filter, sql);
		appendWHEREs(filter, sql);
		appendSort(filter, sql);
		appendPaging(filter, sql);
		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(StoreFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendJOINs(filter, sql);
		appendWHEREs(filter, sql);
		return executeCountQuery(sql.toString());

	}

	private void appendJOINs(final StoreFilter filter, final StringBuilder sb) {
		// nothing yet
	}

	private void appendWHEREs(final StoreFilter filter, final StringBuilder sb) {
		// nothing yet
	}

	@Override
	protected String getTableName() {
		return "store";
	}

	@Override
	public void save(IStore[] entities) {
		try (Connection c = getConnection()) {
			c.setAutoCommit(false);
			try {

				for (IStore entity : entities) {
					PreparedStatement pStmt = c.prepareStatement(
							String.format("insert into %s (name, manager_id, created, updated) values(?,?,?,?)",
									getTableName()),
							Statement.RETURN_GENERATED_KEYS);

	//				pStmt.setString(1, entity.getName());
	//				pStmt.setInt(2, entity.getUserAccount().getId());
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
	public IStore getStoreByType(StoreType storeType) {
		// TODO Auto-generated method stub
		return null;
	}

}
