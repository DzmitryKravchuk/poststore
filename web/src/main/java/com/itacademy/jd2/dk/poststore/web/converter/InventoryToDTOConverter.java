package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IInventory;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.web.dto.InventoryDTO;

@Component
public class InventoryToDTOConverter implements Function<IInventory, InventoryDTO> {

	@Override
	public InventoryDTO apply(final IInventory entity) {
		final InventoryDTO InventoryDTO = new InventoryDTO();
		InventoryDTO.setId(entity.getId());
		InventoryDTO.setVersion(entity.getVersion());
		InventoryDTO.setQuantity(entity.getQuantity());
		InventoryDTO.setCreated(entity.getCreated());
		InventoryDTO.setUpdated(entity.getUpdated());

		final IProduct product = entity.getProduct();
		if (product != null) {
			InventoryDTO.setProductId(product.getId());
			InventoryDTO.setProductName(product.getName());
		}

		final IStore store = entity.getStore();
		if (store != null) {
			InventoryDTO.setStoreId(store.getId());
			InventoryDTO.setStoreName(store.getStoreType().name());
		}

		return InventoryDTO;
	}

}
