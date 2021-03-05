package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICountry;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ILetterZone;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IParcelZone;
import com.itacademy.jd2.dk.poststore.web.dto.CountryDTO;

@Component
public class CountryToDTOConverter implements Function<ICountry, CountryDTO>{

	@Override
	public CountryDTO apply(final ICountry entity) {
		 final CountryDTO countryDto = new CountryDTO();
	        countryDto.setId(entity.getId());
	        countryDto.setName(entity.getName());
	        countryDto.setCreated(entity.getCreated());
	        countryDto.setUpdated(entity.getUpdated());

	        final IExpressZone expressZone = entity.getExpressZone();
	        if (expressZone != null) {
	            countryDto.setExpressZoneId(expressZone.getId());
	            countryDto.setExpressPrice(expressZone.getPrice4g100());
	        }
	        
	        final ILetterZone letterZone = entity.getLetterZone();
	        if (letterZone != null) {
	            countryDto.setLetterZoneId(letterZone.getId());
	            countryDto.setLetterPrice(letterZone.getPrice4g100());
	        }
	       
	        final IParcelZone parcelZone = entity.getParcelZone();
	        if (parcelZone != null) {
	            countryDto.setParcelZoneId(parcelZone.getId());
	            countryDto.setParcelPrice(parcelZone.getPrice4g100());
	        }
	        
	        return countryDto;
	}

}
