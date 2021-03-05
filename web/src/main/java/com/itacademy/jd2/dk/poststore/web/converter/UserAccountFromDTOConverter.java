package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccountDetails;
import com.itacademy.jd2.dk.poststore.service.IUserAccountService;
import com.itacademy.jd2.dk.poststore.web.dto.UserAccountDTO;

@Component
public class UserAccountFromDTOConverter implements Function<UserAccountDTO, IUserAccount> {

	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public IUserAccount apply(final UserAccountDTO dto) {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setId(dto.getId());
		entity.seteMail(dto.geteMail());
		entity.setPassword(dto.getPassword());
		entity.setUserRole(UserRole.valueOf(dto.getUserRole()));

		final IUserAccountDetails detailsEntity = userAccountService.createUserAccountDetailsEntity();
		detailsEntity.setId(dto.getId());
		detailsEntity.setName(dto.getDetails().getName());
		detailsEntity.setAdress(dto.getDetails().getAdress());
		detailsEntity.setPhone(dto.getDetails().getPhone());
		entity.setUserAccountDetails(detailsEntity);
		return entity;
	}

}
