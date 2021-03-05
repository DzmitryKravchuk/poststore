package com.itacademy.jd2.dk.poststore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IParcelZone;

public class ParcelZoneServiceTest extends AbstractTest {
	@Test
	public void testCreate() {
		final IParcelZone entity = saveNewParcelZone();

		final IParcelZone entityFromDb = parcelZoneService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getPrice4g100(), entityFromDb.getPrice4g100());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

//	@Test
//	public void testCreateMultiple() {
//		int initialSize = parcelZoneService.getAll().size();

//		final IParcelZone entity1 = parcelZoneService.createEntity();
//		entity1.setPrice4g100(getRandomMoneyValue());

//		try {
//			final IParcelZone entity2 = parcelZoneService.createEntity();
//			parcelZoneService.save(entity1, entity2);
//			fail("ParcelZone save should fail if name not specified");
//		} catch (Exception e) {
//			assertEquals(initialSize, parcelZoneService.getAll().size());
//		}

//	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IParcelZone entity = saveNewParcelZone();

		Double newPrice = entity.getPrice4g100() + 100;
		entity.setPrice4g100(newPrice);
		Thread.sleep(10);
		parcelZoneService.save(entity);

		final IParcelZone entityFromDb = parcelZoneService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().getTime() > entity.getCreated().getTime());
	}

	@Test
	public void testGetAll() {
		final int intialCount = parcelZoneService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewParcelZone();
		}

		final List<IParcelZone> allEntities = parcelZoneService.getAll();

		for (final IParcelZone entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getPrice4g100());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IParcelZone entity = saveNewParcelZone();
		parcelZoneService.delete(entity.getId());
		assertNull(parcelZoneService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewParcelZone();
		parcelZoneService.deleteAll();
		assertEquals(0, parcelZoneService.getAll().size());
	}
}
