package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICountry;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMailing;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.web.dto.MailingDTO;

@Component
public class MailingToDTOConverter implements Function<IMailing, MailingDTO> {

	@Override
	public MailingDTO apply(final IMailing entity) {
		final MailingDTO mailingDto = new MailingDTO();

		mailingDto.setId(entity.getId());
		mailingDto.setCreated(entity.getCreated());
		mailingDto.setUpdated(entity.getUpdated());
		mailingDto.setAddress(entity.getAddress());
		mailingDto.setAddressee(entity.getAddressee());
		mailingDto.setMailingType(entity.getMailingType().name());
		mailingDto.setWeight(entity.getWeight());
		mailingDto.setPrice(entity.getPrice());

		final ICountry country = entity.getCountry();
		if (country != null) {
			mailingDto.setCountryId(country.getId());
			mailingDto.setCountryName(country.getName());
		}

		final IUserAccount userAccount = entity.getUserAccount();
		if (userAccount != null) {
			mailingDto.setUserAccountId(userAccount.getId());
		}

		return mailingDto;
	}

}
