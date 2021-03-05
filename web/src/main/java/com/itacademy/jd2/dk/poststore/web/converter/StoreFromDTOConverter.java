package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.StoreType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.service.IStoreService;
import com.itacademy.jd2.dk.poststore.web.dto.StoreDTO;

@Component
public class StoreFromDTOConverter implements Function<StoreDTO, IStore> {

	@Autowired
	private IStoreService productService;

	@Override
	public IStore apply(final StoreDTO dto) {
		final IStore entity = productService.createEntity();
		entity.setId(dto.getId());
		entity.setStoreType(StoreType.valueOf(dto.getStoreType()));
		return entity;
	}
}
