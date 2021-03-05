package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;
import com.itacademy.jd2.dk.poststore.web.dto.ExpressZoneDTO;

@Component
public class ExpressZoneToDTOConverter implements Function<IExpressZone, ExpressZoneDTO> {

	@Override
	public ExpressZoneDTO apply(final IExpressZone entity) {
		final ExpressZoneDTO dto = new ExpressZoneDTO();
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		dto.setPrice4g100(entity.getPrice4g100());
		return dto;
	}

}
