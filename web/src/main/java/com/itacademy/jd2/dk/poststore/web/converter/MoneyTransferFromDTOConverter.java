package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMoneyTransfer;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.service.IMoneyTransferService;
import com.itacademy.jd2.dk.poststore.service.IUserAccountService;
import com.itacademy.jd2.dk.poststore.web.dto.MoneyTransferDTO;

@Component
public class MoneyTransferFromDTOConverter implements Function<MoneyTransferDTO, IMoneyTransfer> {

	@Autowired
	private IMoneyTransferService moneyTransferService;
	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public IMoneyTransfer apply(final MoneyTransferDTO dto) {
		final IMoneyTransfer entity = moneyTransferService.createEntity();
		entity.setId(dto.getId());
		entity.setAmount(dto.getAmount());
		entity.setBeneficiaryAdress(dto.getBeneficiaryAdress());
		entity.setBeneficiaryName(dto.getBeneficiaryName());
		entity.setPayerAdress(dto.getPayerAdress());
		entity.setPayerName(dto.getPayerName());

		final IUserAccount userAccount = userAccountService.createEntity();
		userAccount.setId(dto.getUserAccountId());
		entity.setUserAccount(userAccount);

		return entity;
	}

}
