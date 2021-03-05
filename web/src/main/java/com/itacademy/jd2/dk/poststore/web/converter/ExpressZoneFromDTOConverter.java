package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;
import com.itacademy.jd2.dk.poststore.service.IExpressZoneService;
import com.itacademy.jd2.dk.poststore.web.dto.ExpressZoneDTO;
@Component
public class ExpressZoneFromDTOConverter implements Function<ExpressZoneDTO, IExpressZone> {

	@Autowired
	private IExpressZoneService expressZoneService;

	@Override
	public IExpressZone apply(final ExpressZoneDTO dto) {
		final IExpressZone entity = expressZoneService.createEntity();
		entity.setId(dto.getId());
		entity.setPrice4g100(dto.getPrice4g100());
		return entity;
	}
}
