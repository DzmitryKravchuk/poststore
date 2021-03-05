package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ILetterZone;
import com.itacademy.jd2.dk.poststore.web.dto.LetterZoneDTO;

@Component
public class LetterZoneToDTOConverter implements Function<ILetterZone, LetterZoneDTO> {

	@Override
	public LetterZoneDTO apply(final ILetterZone entity) {
		final LetterZoneDTO dto = new LetterZoneDTO();
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		dto.setPrice4g100(entity.getPrice4g100());
		return dto;
	}

}
