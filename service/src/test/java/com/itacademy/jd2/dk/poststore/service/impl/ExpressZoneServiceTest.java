package com.itacademy.jd2.dk.poststore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;

public class ExpressZoneServiceTest extends AbstractTest {
	@Test
	public void testCreate() {
		final IExpressZone entity = saveNewExpressZone();

		final IExpressZone entityFromDb = expressZoneService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getPrice4g100(), entityFromDb.getPrice4g100());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	// @Test
//	public void testCreateMultiple() {
//		int initialSize = expressZoneService.getAll().size();

//		final IExpressZone entity1 = expressZoneService.createEntity();
//		entity1.setPrice4g100(getRandomMoneyValue());

//		try {
//			final IExpressZone entity2 = expressZoneService.createEntity();
//			expressZoneService.save(entity1, entity2);
//			fail("ExpressZone save should fail if name not specified");
//		} catch (Exception e) {
//			assertEquals(initialSize, expressZoneService.getAll().size());
//		}

//	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IExpressZone entity = saveNewExpressZone();

		Double newPrice = entity.getPrice4g100() + 100;
		entity.setPrice4g100(newPrice);
		Thread.sleep(10);
		expressZoneService.save(entity);

		final IExpressZone entityFromDb = expressZoneService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().getTime() > entity.getCreated().getTime());
	}

	@Test
	public void testGetAll() {
		final int intialCount = expressZoneService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewExpressZone();
		}

		final List<IExpressZone> allEntities = expressZoneService.getAll();

		for (final IExpressZone entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getPrice4g100());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IExpressZone entity = saveNewExpressZone();
		expressZoneService.delete(entity.getId());
		assertNull(expressZoneService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewExpressZone();
		expressZoneService.deleteAll();
		assertEquals(0, expressZoneService.getAll().size());
	}
}
