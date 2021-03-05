package com.itacademy.jd2.dk.poststore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccountDetails;

public class UserAccountServiceTest extends AbstractTest {

	@Test
	public void createCustomerTest() {
		final IUserAccount entity = userAccountService.createEntity();
		entity.seteMail("user@-" + getRandomPrefix());
		entity.setPassword("pass-" + getRandomPrefix());
		entity.setUserRole(UserRole.customer);
		final IUserAccountDetails customerDetailsEntity = userAccountService.createUserAccountDetailsEntity();
		customerDetailsEntity.setName("customer name-" + getRandomPrefix());
		customerDetailsEntity.setAdress("adress-" + getRandomPrefix());
		customerDetailsEntity.setPhone(getRandomInt());
		entity.setUserAccountDetails(customerDetailsEntity);

		userAccountService.save(entity);

		final IUserAccount entityFromDb = userAccountService.get(entity.getId());

		assertEquals(entity.geteMail(), entityFromDb.geteMail());
		assertEquals(entity.getPassword(), entityFromDb.getPassword());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		final IUserAccountDetails customerDetailsFromDB = entityFromDb.getUserAccountDetails();
		assertNotNull(customerDetailsFromDB.getId());
		assertEquals(customerDetailsEntity.getName(), customerDetailsFromDB.getName());
		assertNotNull(customerDetailsFromDB.getCreated());
		assertNotNull(customerDetailsFromDB.getUpdated());
		assertTrue(customerDetailsFromDB.getCreated().equals(customerDetailsFromDB.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IUserAccount entity = saveNewUserAccount();

		String neweMail = entity.geteMail() + "_updated";
		entity.seteMail(neweMail);
		Thread.sleep(10);
		userAccountService.save(entity);

		final IUserAccount entityFromDb = userAccountService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().getTime() > entity.getCreated().getTime());
	}

	@Test
	public void testGetAll() {
		final int intialCount = userAccountService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewUserAccount();
		}

		final List<IUserAccount> allEntities = userAccountService.getAll();

		for (final IUserAccount entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getPassword());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IUserAccount entity = saveNewUserAccount();

		userAccountService.delete(entity.getId());
		assertNull(userAccountService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		userAccountService.deleteAll();
		assertEquals(0, userAccountService.getAll().size());
	}

}
