package com.itacademy.jd2.dk.poststore.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.dk.poststore.dao.api.ICountryDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICountry;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ILetterZone;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IParcelZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.CountryFilter;
import com.itacademy.jd2.dk.poststore.jdbc.impl.entity.Country;
import com.itacademy.jd2.dk.poststore.jdbc.impl.entity.ExpressZone;
import com.itacademy.jd2.dk.poststore.jdbc.impl.entity.LetterZone;
import com.itacademy.jd2.dk.poststore.jdbc.impl.entity.ParcelZone;
import com.itacademy.jd2.dk.poststore.jdbc.impl.util.SQLExecutionException;

@Repository
public class CountryDaoImpl extends AbstractDaoImpl<ICountry, Integer> implements ICountryDao {

	@Override
	public ICountry createEntity() {
		return new Country();
	}

	@Override
	public void insert(final ICountry entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"insert into %s (name, created, updated, express_zone_id, letter_zone_id, parcel_zone_id) values(?,?,?,?,?,?)",
						getTableName()), Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getExpressZone().getId());
				pStmt.setInt(5, entity.getLetterZone().getId());
				pStmt.setInt(6, entity.getParcelZone().getId());
				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();
				entity.setId(id);
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
	public void update(final ICountry entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"update %s set name=?, updated=?, express_zone_id=?, letter_zone_id=?, parcel_zone_id=? where id=?",
						getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getExpressZone().getId());
				pStmt.setInt(4, entity.getLetterZone().getId());
				pStmt.setInt(5, entity.getParcelZone().getId());
				pStmt.setInt(6, entity.getId());
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
	protected String getTableName() {
		return "country";
	}

	@Override
	protected ICountry parseRow(final ResultSet resultSet) throws SQLException {
		final ICountry entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final IExpressZone zoneE = new ExpressZone();
		zoneE.setId((Integer) resultSet.getObject("express_zone_id"));
		entity.setExpressZone(zoneE);

		final ILetterZone zoneL = new LetterZone();
		zoneL.setId((Integer) resultSet.getObject("letter_zone_id"));
		entity.setLetterZone(zoneL);

		final IParcelZone zoneP = new ParcelZone();
		zoneP.setId((Integer) resultSet.getObject("parcel_zone_id"));
		entity.setParcelZone(zoneP);
		return entity;
	}

	@Override
	public void save(ICountry... entities) {
		try (Connection c = getConnection()) {
			c.setAutoCommit(false);
			try {

				for (ICountry entity : entities) {
					PreparedStatement pStmt = c.prepareStatement(String.format(
							"insert into %s (name, created, updated, express_zone_id, letter_zone_id, parcel_zone_id) values(?,?,?,?,?,?)",
							getTableName()), Statement.RETURN_GENERATED_KEYS);

					pStmt.setString(1, entity.getName());
					pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
					pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
					pStmt.setInt(4, entity.getExpressZone().getId());
					pStmt.setInt(5, entity.getLetterZone().getId());
					pStmt.setInt(6, entity.getParcelZone().getId());

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
	public List<ICountry> find(CountryFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(CountryFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public ICountry getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
