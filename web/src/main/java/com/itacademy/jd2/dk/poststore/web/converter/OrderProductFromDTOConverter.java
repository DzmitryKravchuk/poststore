package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.ShippingType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.Status;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.service.IOrderProductService;
import com.itacademy.jd2.dk.poststore.service.IUserAccountService;
import com.itacademy.jd2.dk.poststore.web.dto.OrderProductDTO;

@Component
public class OrderProductFromDTOConverter implements Function<OrderProductDTO, IOrderProduct> {

	@Autowired
	private IOrderProductService orderrService;
	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public IOrderProduct apply(final OrderProductDTO dto) {
		final IOrderProduct entity = orderrService.createEntity();
		entity.setId(dto.getId());
		entity.setShippingTo(dto.getShippingTo());
		entity.setShippingType(ShippingType.valueOf(dto.getShippingType()));
		entity.setStatus(Status.valueOf(dto.getStatus()));
		entity.setCost(dto.getCost());

		final IUserAccount userAccount = userAccountService.get(dto.getUserAccountId());
		entity.setUserAccount(userAccount);

		return entity;
	}
}
