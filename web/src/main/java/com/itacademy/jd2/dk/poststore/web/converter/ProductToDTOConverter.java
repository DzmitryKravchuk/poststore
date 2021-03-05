package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.web.dto.ProductDTO;

@Component
public class ProductToDTOConverter implements Function<IProduct, ProductDTO> {
	@Override
	public ProductDTO apply(final IProduct entity) {
		final ProductDTO dto = new ProductDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		dto.setPrice(entity.getPrice());
		return dto;
	}
}
