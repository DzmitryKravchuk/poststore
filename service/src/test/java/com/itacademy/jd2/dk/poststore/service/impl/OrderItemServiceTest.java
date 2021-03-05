package com.itacademy.jd2.dk.poststore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderItem;

public class OrderItemServiceTest extends AbstractTest {

	@Test
	public void createTest() {
		final IOrderItem entity = saveNewOrderItem();

		final IOrderItem entityFromDb = orderItemService.get(entity.getId());

		assertEquals(entity.getQuantity(), entityFromDb.getQuantity());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		// final IProduct productFromDb =
		// productService.get(entity.getProduct().getId());
		// entityFromDb.setProduct(productFromDb);
		// assertEquals(entity.getProduct().getName(),
		// entityFromDb.getProduct().getName());
		// assertEquals(entity.getProduct().getPrice(),
		// entityFromDb.getProduct().getPrice());
		// assertNotNull(entityFromDb.getProduct().getCreated());
		// assertNotNull(entityFromDb.getProduct().getUpdated());
	}

	@Test
	public void createWithFullInfoTest() {
		final IOrderItem entity = saveNewOrderItem();
		final IOrderItem entityFromDb = orderItemService.getFullInfo(entity.getId());

		assertEquals(entity.getOrderProduct().getId(), entityFromDb.getOrderProduct().getId());
		assertEquals(entity.getOrderProduct().getShippingTo(), entityFromDb.getOrderProduct().getShippingTo());
		assertEquals(entity.getOrderProduct().getShippingType(), entityFromDb.getOrderProduct().getShippingType());
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IOrderItem entity = saveNewOrderItem();

		Integer newQuantity = entity.getQuantity() + getRandomInt();
		entity.setQuantity(newQuantity);
		Thread.sleep(10);
		orderItemService.save(entity);

		final IOrderItem entityFromDb = orderItemService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().getTime() > entity.getCreated().getTime());
	}

	@Test
	public void testGetAll() {
		final int intialCount = orderItemService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewOrderItem();
		}

		final List<IOrderItem> allEntities = orderItemService.getAll();

		for (final IOrderItem entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getQuantity());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IOrderItem entity = saveNewOrderItem();

		orderItemService.delete(entity.getId());
		assertNull(orderItemService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		orderItemService.deleteAll();
		assertEquals(0, orderItemService.getAll().size());
	}
}
