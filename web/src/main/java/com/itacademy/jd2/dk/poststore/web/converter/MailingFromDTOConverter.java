package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.MailingType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICountry;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMailing;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.service.ICountryService;
import com.itacademy.jd2.dk.poststore.service.IMailingService;
import com.itacademy.jd2.dk.poststore.service.IUserAccountService;
import com.itacademy.jd2.dk.poststore.web.dto.MailingDTO;

@Component
public class MailingFromDTOConverter implements Function<MailingDTO, IMailing> {

	@Autowired
	private IMailingService mailingService;

	@Autowired
	private ICountryService countryService;

	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public IMailing apply(final MailingDTO dto) {
		final IMailing entity = mailingService.createEntity();
		entity.setId(dto.getId());
		entity.setAddress(dto.getAddress());
		entity.setAddressee(dto.getAddressee());
		entity.setMailingType(MailingType.valueOf(dto.getMailingType()));
		entity.setWeight(dto.getWeight());

		final IUserAccount userAccount = userAccountService.createEntity();
		userAccount.setId(dto.getUserAccountId());
		entity.setUserAccount(userAccount);

		final ICountry country = countryService.getFullInfo(dto.getCountryId());
		entity.setCountry(country);

		return entity;
	}

}
