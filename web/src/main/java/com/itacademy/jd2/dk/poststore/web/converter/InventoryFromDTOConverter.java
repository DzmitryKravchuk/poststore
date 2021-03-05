package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IInventory;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.service.IInventoryService;
import com.itacademy.jd2.dk.poststore.service.IProductService;
import com.itacademy.jd2.dk.poststore.service.IStoreService;
import com.itacademy.jd2.dk.poststore.web.dto.InventoryDTO;

@Component
public class InventoryFromDTOConverter implements Function<InventoryDTO, IInventory> {

	@Autowired
	private IInventoryService inventoryService;
	@Autowired
	private IStoreService storeService;
	@Autowired
	private IProductService productService;

	@Override
	public IInventory apply(final InventoryDTO dto) {
		final IInventory entity = inventoryService.createEntity();
		entity.setId(dto.getId());
		entity.setVersion(dto.getVersion());
		entity.setQuantity(dto.getQuantity());

		final IStore store = storeService.createEntity();
		store.setId(dto.getStoreId());
		entity.setStore(store);

		final IProduct product = productService.createEntity();
		product.setId(dto.getProductId());
		entity.setProduct(product);

		return entity;
	}
}
