package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICourier;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.service.ICourierService;
import com.itacademy.jd2.dk.poststore.service.IUserAccountService;
import com.itacademy.jd2.dk.poststore.web.dto.CourierDTO;

@Component
public class CourierFromDTOConverter implements Function<CourierDTO, ICourier> {

	@Autowired
	private ICourierService courierService;
	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public ICourier apply(final CourierDTO dto) {
		final ICourier entity = courierService.createEntity();
		entity.setId(dto.getId());
		entity.setShippingFrom(dto.getShippingFrom());
		entity.setShippingTo(dto.getShippingTo());
		entity.setAddressee(dto.getAddressee());

		final IUserAccount userAccount = userAccountService.createEntity();
		userAccount.setId(dto.getUserAccountId());
		entity.setUserAccount(userAccount);

		return entity;
	}

}
