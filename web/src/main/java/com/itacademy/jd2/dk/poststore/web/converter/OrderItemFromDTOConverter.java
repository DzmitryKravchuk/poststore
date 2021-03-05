package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderItem;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.service.IOrderItemService;
import com.itacademy.jd2.dk.poststore.service.IOrderProductService;
import com.itacademy.jd2.dk.poststore.service.IProductService;
import com.itacademy.jd2.dk.poststore.web.dto.OrderItemDTO;

@Component
public class OrderItemFromDTOConverter implements Function<OrderItemDTO, IOrderItem> {

	@Autowired
	private IOrderItemService orderItemService;

	@Autowired
	private IProductService productService;

	@Autowired
	private IOrderProductService orderProductService;

	@Override
	public IOrderItem apply(final OrderItemDTO dto) {
		final IOrderItem entity = orderItemService.createEntity();
		entity.setId(dto.getId());
		entity.setQuantity(dto.getQuantity());

		final IOrderProduct orderProduct = orderProductService.get(dto.getOrderProductId());
		entity.setOrderProduct(orderProduct);

		final IProduct product = productService.get(dto.getProductId());
		entity.setProduct(product);

		return entity;
	}

}
