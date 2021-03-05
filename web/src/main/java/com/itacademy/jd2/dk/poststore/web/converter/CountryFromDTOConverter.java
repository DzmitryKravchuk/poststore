package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICountry;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ILetterZone;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IParcelZone;
import com.itacademy.jd2.dk.poststore.service.ICountryService;
import com.itacademy.jd2.dk.poststore.service.IExpressZoneService;
import com.itacademy.jd2.dk.poststore.service.ILetterZoneService;
import com.itacademy.jd2.dk.poststore.service.IParcelZoneService;
import com.itacademy.jd2.dk.poststore.web.dto.CountryDTO;
@Component
public class CountryFromDTOConverter implements Function<CountryDTO, ICountry> {

	@Autowired
	private ICountryService countryService;
	@Autowired
	private IExpressZoneService expressZoneService;
	@Autowired
	private ILetterZoneService letterZoneService;
	@Autowired
	private IParcelZoneService parcelZoneService;

	@Override
	public ICountry apply(final CountryDTO dto) {
		final ICountry entity = countryService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());

		final IExpressZone expressZone = expressZoneService.createEntity();
		expressZone.setId(dto.getExpressZoneId());
		entity.setExpressZone(expressZone);

		final ILetterZone letterZone = letterZoneService.createEntity();
		letterZone.setId(dto.getLetterZoneId());
		entity.setLetterZone(letterZone);
		
		final IParcelZone parcelZone = parcelZoneService.createEntity();
		parcelZone.setId(dto.getParcelZoneId());
		entity.setParcelZone(parcelZone);
		
		return entity;
	}
}
