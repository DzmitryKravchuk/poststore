package com.itacademy.jd2.dk.poststore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderItem;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;

public class OrderProductServiceTest extends AbstractTest {
	@Test
	public void testCreate() {
		final IOrderProduct entity = saveNewOrderProduct();
		final IOrderProduct entityFromDb = orderProductService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		assertTrue(entityFromDb.getStatus().equals(entityFromDb.getStatus()));
		assertTrue(entityFromDb.getShippingType().equals(entityFromDb.getShippingType()));

	}

	@Test
	public void testCreateWithFullInfoTest() {
		final IOrderProduct entity = saveNewOrderProduct();
		final IOrderProduct entityFromDb = orderProductService.getFullInfo(entity.getId());

		assertEquals(entity.getUserAccount().getId(), entityFromDb.getUserAccount().getId());
		assertEquals(entity.getUserAccount().geteMail(), entityFromDb.getUserAccount().geteMail());
		assertEquals(entity.getUserAccount().getPassword(), entityFromDb.getUserAccount().getPassword());
	}

	@Test
	public void testCreateWithItems() {
		final IOrderProduct entity = saveNewOrderProduct();

		final IOrderItem orderItemEntity = orderItemService.createEntity();
		orderItemEntity.setProduct(saveNewProduct());
		orderItemEntity.setQuantity(getRandomInt());
		orderItemEntity.setOrderProduct(entity);
		orderItemService.save(orderItemEntity);

		final IOrderProduct entityFromDb = orderProductService.getFullInfo(entity.getId());

		assertEquals(entity.getUserAccount().getId(), entityFromDb.getUserAccount().getId());
		assertEquals(entity.getUserAccount().geteMail(), entityFromDb.getUserAccount().geteMail());
		assertEquals(entity.getUserAccount().getPassword(), entityFromDb.getUserAccount().getPassword());
	}

	@Test
	public void testCreateWithUser() {
		final IUserAccount userAccountEntity = saveNewUserAccount();

		final IOrderProduct entity = orderProductService.getProductCart(userAccountEntity);

		final IOrderProduct entityFromDb = orderProductService.getFullInfo(entity.getId());

		assertEquals(entity.getUserAccount().getId(), entityFromDb.getUserAccount().getId());
		assertEquals(entity.getUserAccount().geteMail(), entityFromDb.getUserAccount().geteMail());
		assertEquals(entity.getUserAccount().getPassword(), entityFromDb.getUserAccount().getPassword());
	}

//	@Test
//	public void testCreateMultiple() {
//		int initialSize = orderrService.getAll().size();

//		final IOrderr entity1 = orderrService.createEntity();
//		entity1.setCustomer(saveNewUserAccount());

//		try {
//			final IOrderr entity2 = orderrService.createEntity();
//			orderrService.save(entity1, entity2);
//			fail("Order save should fail if name not specified");
//		} catch (Exception e) {
//			assertEquals(initialSize, orderrService.getAll().size());
//		}

//	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IOrderProduct entity = saveNewOrderProduct();

		entity.setUserAccount(saveNewUserAccount());
		;
		Thread.sleep(10);
		orderProductService.save(entity);

		final IOrderProduct entityFromDb = orderProductService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().getTime() > entity.getCreated().getTime());
	}

	@Test
	public void testGetAll() {
		final int intialCount = orderProductService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewOrderProduct();
		}

		final List<IOrderProduct> allEntities = orderProductService.getAll();

		for (final IOrderProduct entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getUserAccount());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IOrderProduct entity = saveNewOrderProduct();
		orderProductService.delete(entity.getId());
		assertNull(orderProductService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewOrderProduct();
		orderProductService.deleteAll();
		assertEquals(0, orderProductService.getAll().size());
	}
}
