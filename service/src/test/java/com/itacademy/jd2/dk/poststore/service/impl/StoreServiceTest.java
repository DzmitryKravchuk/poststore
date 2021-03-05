package com.itacademy.jd2.dk.poststore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.StoreType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;

public class StoreServiceTest extends AbstractTest {
	@Test
	public void testCreate() {
		final IStore entity = saveNewStore();

		final IStore entityFromDb = storeService.get(entity.getId());

		assertEquals(entity.getStoreType(), entityFromDb.getStoreType());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void getStoreByType() {
		final IStore entity = storeService.getStoreByType(StoreType.reserved);

		final IStore entityFromDb = storeService.get(entity.getId());

		assertEquals(entity.getStoreType(), entityFromDb.getStoreType());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IStore entity = saveNewStore();

		entity.setStoreType(StoreType.available);
		entity.setStoreType(StoreType.reserved);
		Thread.sleep(10);
		storeService.save(entity);

		final IStore entityFromDb = storeService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().getTime() > entity.getCreated().getTime());
	}

	@Test
	public void testGetAll() {
		final int intialCount = storeService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewStore();
		}

		final List<IStore> allEntities = storeService.getAll();

		for (final IStore entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IStore entity = saveNewStore();
		storeService.delete(entity.getId());
		assertNull(storeService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewStore();
		storeService.deleteAll();
		assertEquals(0, storeService.getAll().size());
	}
}
