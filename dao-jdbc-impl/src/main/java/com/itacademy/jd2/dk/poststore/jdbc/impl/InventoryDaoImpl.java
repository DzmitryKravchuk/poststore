package com.itacademy.jd2.dk.poststore.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.dk.poststore.dao.api.IInventoryDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IInventory;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.dao.api.filter.InventoryFilter;
import com.itacademy.jd2.dk.poststore.jdbc.impl.entity.Inventory;
import com.itacademy.jd2.dk.poststore.jdbc.impl.entity.Product;
import com.itacademy.jd2.dk.poststore.jdbc.impl.entity.Store;
import com.itacademy.jd2.dk.poststore.jdbc.impl.util.SQLExecutionException;

@Repository
public class InventoryDaoImpl extends AbstractDaoImpl<IInventory, Integer> implements IInventoryDao {

	@Override
	public IInventory createEntity() {
		return new Inventory();
	}

	protected IInventory parseRow(final ResultSet resultSet) throws SQLException {
		final IInventory entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setQuantity(resultSet.getInt("quantity"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final IStore store = new Store();
		store.setId((Integer) resultSet.getObject("store_id"));
		entity.setStore(store);

		final IProduct product = new Product();
		product.setId((Integer) resultSet.getObject("product_id"));
		entity.setProduct(product);

		return entity;
	}

	@Override
	public void update(IInventory entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"update %s set updated=?, quantity=?, product_id=?, store_id=? where id=?", getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setObject(1, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(2, entity.getQuantity());
				pStmt.setInt(3, entity.getProduct().getId());
				pStmt.setInt(4, entity.getStore().getId());
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
	public void insert(IInventory entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"insert into %s (quantity, product_id, store_id, created, updated) values(?,?,?,?,?)",
						getTableName()), Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getQuantity());
				pStmt.setInt(2, entity.getProduct().getId());
				pStmt.setInt(3, entity.getStore().getId());
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
	public List<IInventory> find(InventoryFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendJOINs(filter, sql);
		appendWHEREs(filter, sql);
		appendSort(filter, sql);
		appendPaging(filter, sql);
		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(InventoryFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendJOINs(filter, sql);
		appendWHEREs(filter, sql);
		return executeCountQuery(sql.toString());

	}

	private void appendJOINs(final InventoryFilter filter, final StringBuilder sb) {
		// nothing yet
	}

	private void appendWHEREs(final InventoryFilter filter, final StringBuilder sb) {
		// nothing yet
	}

	@Override
	protected String getTableName() {
		return "inventory";
	}

	@Override
	public void save(IInventory[] entities) {
		try (Connection c = getConnection()) {
			c.setAutoCommit(false);
			try {

				for (IInventory entity : entities) {
					PreparedStatement pStmt = c.prepareStatement(String.format(
							"insert into %s (quantity, product_id, store_id, created, updated) values(?,?,?,?,?)",
							getTableName()), Statement.RETURN_GENERATED_KEYS);

					pStmt.setInt(1, entity.getQuantity());
					pStmt.setInt(2, entity.getProduct().getId());
					pStmt.setInt(3, entity.getStore().getId());
					pStmt.setObject(4, entity.getCreated(), Types.TIMESTAMP);
					pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);

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
	public IInventory getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IInventory getInventoryItem(IStore store, IProduct product) {
		// TODO Auto-generated method stub
		return null;
	}
}
