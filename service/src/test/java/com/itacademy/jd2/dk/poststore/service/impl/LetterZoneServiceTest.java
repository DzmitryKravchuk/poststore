package com.itacademy.jd2.dk.poststore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ILetterZone;

public class LetterZoneServiceTest extends AbstractTest {
	@Test
	public void testCreate() {
		final ILetterZone entity = saveNewLetterZone();

		final ILetterZone entityFromDb = letterZoneService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getPrice4g100(), entityFromDb.getPrice4g100());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	// @Test
//	public void testCreateMultiple() {
//		int initialSize = letterZoneService.getAll().size();

//		final ILetterZone entity1 = letterZoneService.createEntity();
//		entity1.setPrice4g100(getRandomMoneyValue());

//		try {
//			final ILetterZone entity2 = letterZoneService.createEntity();
//			letterZoneService.save(entity1, entity2);
//			fail("LetterZone save should fail if name not specified");
//		} catch (Exception e) {
//			assertEquals(initialSize, letterZoneService.getAll().size());
//		}

//	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ILetterZone entity = saveNewLetterZone();

		Double newPrice = entity.getPrice4g100() + 100;
		entity.setPrice4g100(newPrice);
		Thread.sleep(10);
		letterZoneService.save(entity);

		final ILetterZone entityFromDb = letterZoneService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().getTime() > entity.getCreated().getTime());
	}

	@Test
	public void testGetAll() {
		final int intialCount = letterZoneService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewLetterZone();
		}

		final List<ILetterZone> allEntities = letterZoneService.getAll();

		for (final ILetterZone entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getPrice4g100());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ILetterZone entity = saveNewLetterZone();
		letterZoneService.delete(entity.getId());
		assertNull(letterZoneService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewLetterZone();
		letterZoneService.deleteAll();
		assertEquals(0, letterZoneService.getAll().size());
	}
}
