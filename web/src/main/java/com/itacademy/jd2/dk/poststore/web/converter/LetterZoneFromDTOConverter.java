package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ILetterZone;
import com.itacademy.jd2.dk.poststore.service.ILetterZoneService;
import com.itacademy.jd2.dk.poststore.web.dto.LetterZoneDTO;
@Component
public class LetterZoneFromDTOConverter implements Function<LetterZoneDTO, ILetterZone> {

	@Autowired
	private ILetterZoneService letterZoneService;

	@Override
	public ILetterZone apply(final LetterZoneDTO dto) {
		final ILetterZone entity = letterZoneService.createEntity();
		entity.setId(dto.getId());
		entity.setPrice4g100(dto.getPrice4g100());
		return entity;
	}
}
