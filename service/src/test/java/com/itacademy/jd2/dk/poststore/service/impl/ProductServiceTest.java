package com.itacademy.jd2.dk.poststore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;

public class ProductServiceTest extends AbstractTest {
	
	@Test
	public void testCreate() {
		final IProduct entity = saveNewProduct();

		final IProduct entityFromDb = productService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testCreateMultiple() {
		int initialSize = productService.getAll().size();

		final IProduct entity1 = productService.createEntity();
		entity1.setName("product-" + getRandomPrefix());

		try {
			final IProduct entity2 = productService.createEntity();
			productService.save(entity1, entity2);
			fail("Product save should fail if name not specified");
		} catch (Exception e) {
			assertEquals(initialSize, productService.getAll().size());
		}

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IProduct entity = saveNewProduct();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(10);
		productService.save(entity);

		final IProduct entityFromDb = productService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().getTime() > entity.getCreated().getTime());
	}

	@Test
	public void testGetAll() {
		final int intialCount = productService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewProduct();
		}

		final List<IProduct> allEntities = productService.getAll();

		for (final IProduct entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IProduct entity = saveNewProduct();
		productService.delete(entity.getId());
		assertNull(productService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewProduct();
		productService.deleteAll();
		assertEquals(0, productService.getAll().size());
	}
}
