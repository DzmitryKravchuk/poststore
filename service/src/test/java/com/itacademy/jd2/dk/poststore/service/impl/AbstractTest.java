package com.itacademy.jd2.dk.poststore.service.impl;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.ImageFormat;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.MailingType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.ShippingType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.Status;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.StoreType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICountry;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICourier;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IInventory;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ILetterZone;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMailing;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMoneyTransfer;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderItem;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IParcelZone;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPolygraphy;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.service.ICountryService;
import com.itacademy.jd2.dk.poststore.service.ICourierService;
import com.itacademy.jd2.dk.poststore.service.ICustomerDetailsService;
import com.itacademy.jd2.dk.poststore.service.IExpressZoneService;
import com.itacademy.jd2.dk.poststore.service.IInventoryService;
import com.itacademy.jd2.dk.poststore.service.ILetterZoneService;
import com.itacademy.jd2.dk.poststore.service.IMailingService;
import com.itacademy.jd2.dk.poststore.service.IMoneyTransferService;
import com.itacademy.jd2.dk.poststore.service.IOrderItemService;
import com.itacademy.jd2.dk.poststore.service.IOrderProductService;
import com.itacademy.jd2.dk.poststore.service.IPaperDetailsService;
import com.itacademy.jd2.dk.poststore.service.IParcelZoneService;
import com.itacademy.jd2.dk.poststore.service.IPolygraphyService;
import com.itacademy.jd2.dk.poststore.service.IProductService;
import com.itacademy.jd2.dk.poststore.service.IStoreService;
import com.itacademy.jd2.dk.poststore.service.IUserAccountService;

@SpringJUnitConfig(locations = "classpath:service-context-test.xml")
public class AbstractTest {

	@Autowired
	protected IInventoryService inventoryService;
	@Autowired
	protected IOrderItemService orderItemService;
	@Autowired
	protected IProductService productService;
	@Autowired
	protected IMailingService mailingService;
	@Autowired
	protected ICountryService countryService;
	@Autowired
	protected IExpressZoneService expressZoneService;
	@Autowired
	protected ILetterZoneService letterZoneService;
	@Autowired
	protected IParcelZoneService parcelZoneService;
	@Autowired
	protected ICourierService courierService;
	@Autowired
	protected IMoneyTransferService moneyTransferService;
	@Autowired
	protected IPolygraphyService polygraphyService;
	@Autowired
	protected IPaperDetailsService paperDetailsService;

	@Autowired
	protected IOrderProductService orderProductService;
	@Autowired
	protected IStoreService storeService;
	@Autowired
	protected ICustomerDetailsService customerDetailsService;

	@Autowired
	protected IUserAccountService userAccountService;

	private static final Random RANDOM = new Random();

	@BeforeEach
	public void setUpMethod() {
		// clean DB recursive

		orderItemService.deleteAll();
		inventoryService.deleteAll();
		productService.deleteAll();

		mailingService.deleteAll();
		countryService.deleteAll();
		expressZoneService.deleteAll();
		letterZoneService.deleteAll();
		parcelZoneService.deleteAll();
		courierService.deleteAll();
		moneyTransferService.deleteAll();
		polygraphyService.deleteAll();
		paperDetailsService.deleteAll();

		orderProductService.deleteAll();
		storeService.deleteAll();
		customerDetailsService.deleteAll();

		userAccountService.deleteAll();
	}

	protected String getRandomPrefix() {
		return RANDOM.nextInt(99999) + "";
	}

	protected Integer getRandomInt() {
		return RANDOM.nextInt(999);
	}

	protected Double getRandomMoneyValue() {
		return Math.round(ThreadLocalRandom.current().nextDouble(0.01, 10) * 100.0) / 100.0;
	}

	protected LocalDate getRandomDate() {
		int minDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2018, 12, 31).toEpochDay();
		long randomDay = minDay + RANDOM.nextInt(maxDay - minDay);
		LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
		return randomDate;
	}

	protected int getRandomObjectsCount() {
		return RANDOM.nextInt(9) + 1;
	}

	public Random getRANDOM() {
		return RANDOM;
	}

	protected IProduct saveNewProduct() {
		final IProduct entity = productService.createEntity();
		entity.setName(getRandomPrefix());
		entity.setPrice(getRandomMoneyValue());
		productService.save(entity);
		return entity;
	}

	protected IExpressZone saveNewExpressZone() {
		final IExpressZone entity = expressZoneService.createEntity();
		entity.setPrice4g100(getRandomMoneyValue());
		expressZoneService.save(entity);
		return entity;
	}

	protected ILetterZone saveNewLetterZone() {
		final ILetterZone entity = letterZoneService.createEntity();
		entity.setPrice4g100(getRandomMoneyValue());
		letterZoneService.save(entity);
		return entity;
	}

	protected IParcelZone saveNewParcelZone() {
		final IParcelZone entity = parcelZoneService.createEntity();
		entity.setPrice4g100(getRandomMoneyValue());
		parcelZoneService.save(entity);
		return entity;
	}

	protected ICountry saveNewCountry() {
		final ICountry entity = countryService.createEntity();
		entity.setName("country-" + getRandomPrefix());

		entity.setExpressZone(saveNewExpressZone());

		entity.setLetterZone(saveNewLetterZone());

		entity.setParcelZone(saveNewParcelZone());

		countryService.save(entity);
		return entity;
	}

	protected ICourier saveNewCourier() {
		final ICourier entity = courierService.createEntity();
		entity.setShippingFrom("from-" + getRandomPrefix());
		entity.setShippingTo("to-" + getRandomPrefix());
		entity.setAddressee("name-" + getRandomPrefix());
		entity.setPrice(getRandomMoneyValue());
		entity.setUserAccount(saveNewUserAccount());
		courierService.save(entity);
		return entity;
	}

	protected IOrderProduct saveNewOrderProduct() {
		final IOrderProduct entity = orderProductService.createEntity();
		entity.setUserAccount(saveNewUserAccount());
		entity.setCost(0.0);
		entity.setStatus(Status.productCart);
		entity.setShippingType(ShippingType.parcelDelivery);
		orderProductService.save(entity);
		return entity;
	}

	protected IUserAccount saveNewUserAccount() {
		final IUserAccount entity = userAccountService.createEntity();
		entity.seteMail("user@-" + getRandomPrefix());
		entity.setPassword("pass-" + getRandomPrefix());
		entity.setUserRole(UserRole.customer);
		userAccountService.save(entity);
		return entity;
	}

	protected IMoneyTransfer saveNewMoneyTransfer() {
		final IMoneyTransfer entity = moneyTransferService.createEntity();
		entity.setPayerName("name-" + getRandomPrefix());
		entity.setPayerAdress("adress-" + getRandomPrefix());
		entity.setBeneficiaryName("name-" + getRandomPrefix());
		entity.setBeneficiaryAdress("adress-" + getRandomPrefix());
		entity.setAmount(getRandomMoneyValue());
		entity.setUserAccount(saveNewUserAccount());
		moneyTransferService.save(entity);
		return entity;
	}

	protected IPolygraphy saveNewPolygraphy() {
		final IPolygraphy entity = polygraphyService.createEntity();
		entity.setCopyCount(getRandomInt());

		final ImageFormat[] allTypes = ImageFormat.values();
		final int randomIndex = Math.max(0, getRANDOM().nextInt(allTypes.length) - 1);
		entity.setFormat(allTypes[randomIndex]);

		entity.setIsColoured(true);
		entity.setIsDuplexPrinting(false);
		entity.setPaperDetails(saveNewPaperDetails());
		entity.setUserAccount(saveNewUserAccount());
		polygraphyService.save(entity);
		return entity;
	}

	protected IPaperDetails saveNewPaperDetails() {
		final IPaperDetails entity = paperDetailsService.createEntity();
		entity.setName("paperBrand-" + getRandomPrefix());
		entity.setPrice4Paper(getRandomMoneyValue());
		paperDetailsService.save(entity);
		return entity;
	}

	protected IMailing saveNewMailing() {
		final IMailing entity = mailingService.createEntity();

		final MailingType[] allTypes = MailingType.values();
		final int randomIndex = Math.max(0, getRANDOM().nextInt(allTypes.length) - 1);
		entity.setMailingType(allTypes[randomIndex]);

		entity.setWeight(getRandomMoneyValue());
		entity.setAddressee("addressee-" + getRandomPrefix());
		entity.setAddress("adress-" + getRandomPrefix());
		entity.setCountry(saveNewCountry());
		entity.setUserAccount(saveNewUserAccount());
		mailingService.save(entity);
		return entity;
	}

	protected IStore saveNewStore() {
		final IStore entity = storeService.createEntity();
		final StoreType[] allTypes = StoreType.values();
		final int randomIndex = Math.max(0, getRANDOM().nextInt(allTypes.length) - 1);
		entity.setStoreType(allTypes[randomIndex]);

		storeService.save(entity);
		return entity;
	}

	protected IOrderItem saveNewOrderItem() {
		final IOrderItem entity = orderItemService.createEntity();
		entity.setProduct(saveNewProduct());
		entity.setQuantity(getRandomInt());
		entity.setOrderProduct(saveNewOrderProduct());
		orderItemService.save(entity);
		return entity;
	}

	protected IInventory saveNewInventory() {
		final IInventory entity = inventoryService.createEntity();
		entity.setQuantity(getRandomInt());
		entity.setProduct(saveNewProduct());
		entity.setStore(saveNewStore());
		entity.setVersion(0);
		inventoryService.save(entity);
		return entity;
	}
}
