package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IParcelZone;
import com.itacademy.jd2.dk.poststore.web.dto.ParcelZoneDTO;

@Component
public class ParcelZoneToDTOConverter implements Function<IParcelZone, ParcelZoneDTO> {

	@Override
	public ParcelZoneDTO apply(final IParcelZone entity) {
		final ParcelZoneDTO dto = new ParcelZoneDTO();
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		dto.setPrice4g100(entity.getPrice4g100());
		return dto;
	}

}
