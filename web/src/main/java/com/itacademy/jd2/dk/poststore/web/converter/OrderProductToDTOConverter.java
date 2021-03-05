package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.web.dto.OrderProductDTO;

@Component
public class OrderProductToDTOConverter implements Function<IOrderProduct, OrderProductDTO> {

	@Override
	public OrderProductDTO apply(final IOrderProduct entity) {
		final OrderProductDTO orderProductDto = new OrderProductDTO();
		orderProductDto.setId(entity.getId());
		orderProductDto.setCreated(entity.getCreated());
		orderProductDto.setUpdated(entity.getUpdated());
		orderProductDto.setShippingTo(entity.getShippingTo());
		orderProductDto.setShippingType(entity.getShippingType().name());
		orderProductDto.setStatus(entity.getStatus().name());
		orderProductDto.setCost(entity.getCost());

		final IUserAccount userAccount = entity.getUserAccount();
		if (userAccount != null) {
			orderProductDto.setUserAccountId(userAccount.getId());
		}

		return orderProductDto;
	}

}
