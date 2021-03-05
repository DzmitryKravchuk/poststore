package com.itacademy.jd2.dk.poststore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICourier;

public class CourierServiceTest extends AbstractTest {

	@Test
	public void createTest() {
		final ICourier entity = saveNewCourier();

		final ICourier entityFromDb = courierService.get(entity.getId());

		assertEquals(entity.getShippingFrom(), entityFromDb.getShippingFrom());
		assertEquals(entity.getShippingTo(), entityFromDb.getShippingTo());
		assertEquals(entity.getAddressee(), entityFromDb.getAddressee());
		assertEquals(entity.getPrice(), entityFromDb.getPrice());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ICourier entity = saveNewCourier();

		String newName = entity.getAddressee() + "_updated";
		entity.setAddressee(newName);
		Thread.sleep(10);
		courierService.save(entity);

		final ICourier entityFromDb = courierService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().getTime() > entity.getCreated().getTime());
	}

	@Test
	public void testGetAll() {
		final int intialCount = courierService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCourier();
		}

		final List<ICourier> allEntities = courierService.getAll();

		for (final ICourier entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getPrice());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ICourier entity = saveNewCourier();

		courierService.delete(entity.getId());
		assertNull(courierService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		courierService.deleteAll();
		assertEquals(0, courierService.getAll().size());
	}

}
