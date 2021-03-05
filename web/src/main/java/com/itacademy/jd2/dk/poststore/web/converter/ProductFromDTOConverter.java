package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.service.IProductService;
import com.itacademy.jd2.dk.poststore.web.dto.ProductDTO;

@Component
public class ProductFromDTOConverter implements Function<ProductDTO, IProduct> {

	@Autowired
	private IProductService productService;

	@Override
	public IProduct apply(final ProductDTO dto) {
		final IProduct entity = productService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setPrice(dto.getPrice());
		return entity;
	}
}
