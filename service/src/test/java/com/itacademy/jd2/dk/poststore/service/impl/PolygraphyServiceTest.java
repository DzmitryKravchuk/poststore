package com.itacademy.jd2.dk.poststore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPolygraphy;

public class PolygraphyServiceTest extends AbstractTest {

	@Test
	public void createTest() {
		final IPolygraphy entity = saveNewPolygraphy();

		final IPolygraphy entityFromDb = polygraphyService.get(entity.getId());
		assertEquals(entity.getFormat(), entityFromDb.getFormat());
		assertEquals(entity.getCopyCount(), entityFromDb.getCopyCount());
		assertEquals(entity.getIsColoured(), entityFromDb.getIsColoured());
		assertEquals(entity.getPrice(), entityFromDb.getPrice());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		final IPaperDetails paperDetailsFromDb = paperDetailsService.get(entity.getPaperDetails().getId());
		entityFromDb.setPaperDetails(paperDetailsFromDb);
		assertEquals(entity.getPaperDetails().getPrice4Paper(), entityFromDb.getPaperDetails().getPrice4Paper());
		assertEquals(entity.getPaperDetails().getName(), entityFromDb.getPaperDetails().getName());
		assertNotNull(entityFromDb.getPaperDetails().getCreated());
		assertNotNull(entityFromDb.getPaperDetails().getUpdated());
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IPolygraphy entity = saveNewPolygraphy();

		Integer newCopyCount = entity.getCopyCount() + 10;
		entity.setCopyCount(newCopyCount);
		Thread.sleep(10);
		polygraphyService.save(entity);

		final IPolygraphy entityFromDb = polygraphyService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().getTime() > entity.getCreated().getTime());
	}

	@Test
	public void testGetAll() {
		final int intialCount = polygraphyService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewPolygraphy();
		}

		final List<IPolygraphy> allEntities = polygraphyService.getAll();

		for (final IPolygraphy entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getPrice());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IPolygraphy entity = saveNewPolygraphy();

		polygraphyService.delete(entity.getId());
		assertNull(polygraphyService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		polygraphyService.deleteAll();
		assertEquals(0, polygraphyService.getAll().size());
	}
}
