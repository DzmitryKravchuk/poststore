package com.itacademy.jd2.dk.poststore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICountry;

public class CountryServiceTest extends AbstractTest {

	@Test
	public void createTest() {
		final ICountry entity = saveNewCountry();

		final ICountry entityFromDb = countryService.get(entity.getId());

		assertEquals(entity.getName(), entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void createWithFullInfoTest() {
		final ICountry entity = saveNewCountry();
		final ICountry entityFromDb = countryService.getFullInfo(entity.getId());
		
		assertEquals(entity.getExpressZone().getId(), entityFromDb.getExpressZone().getId());
		assertEquals(entity.getLetterZone().getId(), entityFromDb.getLetterZone().getId());
		assertEquals(entity.getParcelZone().getId(), entityFromDb.getParcelZone().getId());
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ICountry entity = saveNewCountry();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(10);
		countryService.save(entity);

		final ICountry entityFromDb = countryService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().getTime() > entity.getCreated().getTime());
	}

	@Test
	public void testGetAll() {
		final int intialCount = countryService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCountry();
		}

		final List<ICountry> allEntities = countryService.getAll();

		for (final ICountry entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ICountry entity = saveNewCountry();

		countryService.delete(entity.getId());
		assertNull(countryService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		countryService.deleteAll();
		assertEquals(0, countryService.getAll().size());
	}

}
