package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;
import com.itacademy.jd2.dk.poststore.service.IPaperDetailsService;
import com.itacademy.jd2.dk.poststore.web.dto.PaperDetailsDTO;

@Component
public class PaperDetailsFromDTOConverter implements Function<PaperDetailsDTO, IPaperDetails> {

	@Autowired
	private IPaperDetailsService paperDetailsService;

	@Override
	public IPaperDetails apply(PaperDetailsDTO dto) {
		final IPaperDetails entity = paperDetailsService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setPrice4Paper(dto.getPrice());
		return entity;
	}
}
