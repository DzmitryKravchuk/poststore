package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.web.dto.StoreDTO;

@Component
public class StoreToDTOConverter implements Function<IStore, StoreDTO> {
	@Override
	public StoreDTO apply(final IStore entity) {
		final StoreDTO dto = new StoreDTO();
		dto.setId(entity.getId());
		dto.setStoreType(entity.getStoreType().name());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}
}
