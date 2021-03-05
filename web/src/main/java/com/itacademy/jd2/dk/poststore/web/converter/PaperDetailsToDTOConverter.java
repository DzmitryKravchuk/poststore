package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;
import com.itacademy.jd2.dk.poststore.web.dto.PaperDetailsDTO;

@Component
public class PaperDetailsToDTOConverter implements Function<IPaperDetails, PaperDetailsDTO> {
	@Override
	public PaperDetailsDTO apply(IPaperDetails entity) {
		final PaperDetailsDTO dto = new PaperDetailsDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		dto.setPrice(entity.getPrice4Paper());
		return dto;
	}
}
