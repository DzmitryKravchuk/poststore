package com.itacademy.jd2.dk.poststore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMoneyTransfer;

public class MoneyTransferServiceTest extends AbstractTest {
	@Test
	public void createTest() {
		final IMoneyTransfer entity = saveNewMoneyTransfer();

		final IMoneyTransfer entityFromDb = moneyTransferService.get(entity.getId());

		assertEquals(entity.getBeneficiaryName(), entityFromDb.getBeneficiaryName());
		assertEquals(entity.getPayerAdress(), entityFromDb.getPayerAdress());
		assertEquals(entity.getAmount(), entityFromDb.getAmount());
		assertEquals(entity.getTransactionPrice(), entityFromDb.getTransactionPrice());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IMoneyTransfer entity = saveNewMoneyTransfer();

		double newAmmount = entity.getAmount() + 100;
		entity.setAmount(newAmmount);
		Thread.sleep(10);
		moneyTransferService.save(entity);

		final IMoneyTransfer entityFromDb = moneyTransferService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().getTime() > entity.getCreated().getTime());
	}

	@Test
	public void testGetAll() {
		final int intialCount = moneyTransferService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewMoneyTransfer();
		}

		final List<IMoneyTransfer> allEntities = moneyTransferService.getAll();

		for (final IMoneyTransfer entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getPayerName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IMoneyTransfer entity = saveNewMoneyTransfer();

		moneyTransferService.delete(entity.getId());
		assertNull(moneyTransferService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		moneyTransferService.deleteAll();
		assertEquals(0, moneyTransferService.getAll().size());
	}

}
