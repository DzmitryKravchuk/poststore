package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMoneyTransfer;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.web.dto.MoneyTransferDTO;

@Component
public class MoneyTransferToDTOConverter implements Function<IMoneyTransfer, MoneyTransferDTO> {

	@Override
	public MoneyTransferDTO apply(final IMoneyTransfer entity) {
		final MoneyTransferDTO moneyTransferDTO = new MoneyTransferDTO();

		moneyTransferDTO.setId(entity.getId());
		moneyTransferDTO.setCreated(entity.getCreated());
		moneyTransferDTO.setUpdated(entity.getUpdated());
		moneyTransferDTO.setAmount(entity.getAmount());
		moneyTransferDTO.setBeneficiaryAdress(entity.getBeneficiaryAdress());
		moneyTransferDTO.setBeneficiaryName(entity.getBeneficiaryName());
		moneyTransferDTO.setPayerAdress(entity.getPayerAdress());
		moneyTransferDTO.setPayerName(entity.getPayerName());

		moneyTransferDTO.setTransactionPrice(entity.getTransactionPrice());

		final IUserAccount userAccount = entity.getUserAccount();
		if (userAccount != null) {
			moneyTransferDTO.setUserAccountId(userAccount.getId());
		}

		return moneyTransferDTO;
	}

}
