package com.itacademy.jd2.dk.poststore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;

public class PaperDetailsServiceTest extends AbstractTest {
	@Test
	public void createTest() {

		final IPaperDetails entity = saveNewPaperDetails();

		final IPaperDetails entityFromDb = paperDetailsService.get(entity.getId());

		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getPrice4Paper(), entityFromDb.getPrice4Paper());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IPaperDetails entity = saveNewPaperDetails();

		double newPrice = entity.getPrice4Paper() + 10;
		entity.setPrice4Paper(newPrice);
		Thread.sleep(10);
		paperDetailsService.save(entity);

		final IPaperDetails entityFromDb = paperDetailsService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().getTime() > entity.getCreated().getTime());
	}

	@Test
	public void testGetAll() {
		final int intialCount = paperDetailsService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewPaperDetails();
		}

		final List<IPaperDetails> allEntities = paperDetailsService.getAll();

		for (final IPaperDetails entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getPrice4Paper());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IPaperDetails entity = saveNewPaperDetails();

		paperDetailsService.delete(entity.getId());
		assertNull(paperDetailsService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		paperDetailsService.deleteAll();
		assertEquals(0, paperDetailsService.getAll().size());
	}
}
