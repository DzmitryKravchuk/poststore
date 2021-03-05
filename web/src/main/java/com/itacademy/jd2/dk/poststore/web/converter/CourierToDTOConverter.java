package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICourier;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.web.dto.CourierDTO;

@Component
public class CourierToDTOConverter implements Function<ICourier, CourierDTO> {

	@Override
	public CourierDTO apply(final ICourier entity) {
		final CourierDTO courierDTO = new CourierDTO();

		courierDTO.setId(entity.getId());
		courierDTO.setCreated(entity.getCreated());
		courierDTO.setUpdated(entity.getUpdated());
		courierDTO.setShippingFrom(entity.getShippingFrom());
		courierDTO.setAddressee(entity.getAddressee());
		courierDTO.setShippingTo(entity.getShippingTo());
		courierDTO.setPrice(entity.getPrice());

		final IUserAccount userAccount = entity.getUserAccount();
		if (userAccount != null) {
			courierDTO.setUserAccountId(userAccount.getId());
		}

		return courierDTO;
	}

}
