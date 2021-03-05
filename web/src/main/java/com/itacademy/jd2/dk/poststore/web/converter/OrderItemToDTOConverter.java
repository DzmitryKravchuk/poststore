package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderItem;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.web.dto.OrderItemDTO;

@Component
public class OrderItemToDTOConverter implements Function<IOrderItem, OrderItemDTO> {

	@Override
	public OrderItemDTO apply(final IOrderItem entity) {
		final OrderItemDTO OrderItemDTO = new OrderItemDTO();

		OrderItemDTO.setId(entity.getId());
		OrderItemDTO.setCreated(entity.getCreated());
		OrderItemDTO.setUpdated(entity.getUpdated());
		OrderItemDTO.setQuantity(entity.getQuantity());

		final IProduct product = entity.getProduct();
		if (product != null) {
			OrderItemDTO.setProductId(product.getId());
			OrderItemDTO.setProductName(product.getName());
			OrderItemDTO.setProductPrice(product.getPrice());
		}

		final IOrderProduct orderProduct = entity.getOrderProduct();
		if (orderProduct != null) {
			OrderItemDTO.setOrderProductId(orderProduct.getId());
		}

		return OrderItemDTO;
	}

}
