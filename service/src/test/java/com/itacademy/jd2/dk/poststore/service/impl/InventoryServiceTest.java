package com.itacademy.jd2.dk.poststore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IInventory;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;

public class InventoryServiceTest extends AbstractTest {

	@Test
	public void createTest() {
		final IInventory entity = saveNewInventory();

		final IInventory entityFromDb = inventoryService.get(entity.getId());

		assertEquals(entity.getQuantity(), entityFromDb.getQuantity());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		final IProduct productFromDb = productService.get(entity.getProduct().getId());
		entityFromDb.setProduct(productFromDb);
		assertEquals(entity.getProduct().getName(), entityFromDb.getProduct().getName());
		assertEquals(entity.getProduct().getPrice(), entityFromDb.getProduct().getPrice());
		assertNotNull(entityFromDb.getProduct().getCreated());
		assertNotNull(entityFromDb.getProduct().getUpdated());

		final IStore storeFromDb = storeService.get(entity.getStore().getId());
		entityFromDb.setStore(storeFromDb);
		assertEquals(entity.getStore().getStoreType(), entityFromDb.getStore().getStoreType());
		assertNotNull(entityFromDb.getProduct().getCreated());
		assertNotNull(entityFromDb.getProduct().getUpdated());
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IInventory entity = saveNewInventory();

		Integer newQuantity = entity.getQuantity() + getRandomInt();
		entity.setQuantity(newQuantity);
		Thread.sleep(10);
		inventoryService.save(entity);

		final IInventory entityFromDb = inventoryService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().getTime() > entity.getCreated().getTime());
	}

	@Test
	public void testGetAll() {
		final int intialCount = inventoryService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewInventory();
		}

		final List<IInventory> allEntities = inventoryService.getAll();

		for (final IInventory entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getQuantity());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IInventory entity = saveNewInventory();

		inventoryService.delete(entity.getId());
		assertNull(inventoryService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		inventoryService.deleteAll();
		assertEquals(0, inventoryService.getAll().size());
	}
}
