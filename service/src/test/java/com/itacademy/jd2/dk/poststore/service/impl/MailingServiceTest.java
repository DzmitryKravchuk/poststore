package com.itacademy.jd2.dk.poststore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMailing;

public class MailingServiceTest extends AbstractTest {
	@Test
	public void createTest() {
		final IMailing entity = saveNewMailing();

		final IMailing entityFromDb = mailingService.get(entity.getId());

		assertEquals(entity.getMailingType(), entityFromDb.getMailingType());
		assertEquals(entity.getAddress(), entityFromDb.getAddress());
		assertEquals(entity.getAddressee(), entityFromDb.getAddressee());
		assertEquals(entity.getPrice(), entityFromDb.getPrice());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IMailing entity = saveNewMailing();

		String newName = entity.getAddressee() + "_updated";
		entity.setAddressee(newName);
		Thread.sleep(10);
		mailingService.save(entity);

		final IMailing entityFromDb = mailingService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().getTime() > entity.getCreated().getTime());
	}

	@Test
	public void testGetAll() {
		final int intialCount = mailingService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewMailing();
		}

		final List<IMailing> allEntities = mailingService.getAll();

		for (final IMailing entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getPrice());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IMailing entity = saveNewMailing();

		mailingService.delete(entity.getId());
		assertNull(mailingService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		mailingService.deleteAll();
		assertEquals(0, mailingService.getAll().size());
	}
}
