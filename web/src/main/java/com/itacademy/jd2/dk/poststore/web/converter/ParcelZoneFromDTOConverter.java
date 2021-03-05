package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IParcelZone;
import com.itacademy.jd2.dk.poststore.service.IParcelZoneService;
import com.itacademy.jd2.dk.poststore.web.dto.ParcelZoneDTO;
@Component
public class ParcelZoneFromDTOConverter implements Function<ParcelZoneDTO, IParcelZone> {

	@Autowired
	private IParcelZoneService parcelZoneService;

	@Override
	public IParcelZone apply(final ParcelZoneDTO dto) {
		final IParcelZone entity = parcelZoneService.createEntity();
		entity.setId(dto.getId());
		entity.setPrice4g100(dto.getPrice4g100());
		return entity;
	}
}
