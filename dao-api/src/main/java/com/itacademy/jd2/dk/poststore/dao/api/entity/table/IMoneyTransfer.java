package com.itacademy.jd2.dk.poststore.dao.api.entity.table;

public interface IMoneyTransfer extends IBaseEntity {

	String getPayerName();

	void setPayerName(String payerName);

	String getPayerAdress();

	void setPayerAdress(String payerAdress);

	String getBeneficiaryName();

	void setBeneficiaryName(String beneficiaryName);

	String getBeneficiaryAdress();

	void setBeneficiaryAdress(String beneficiaryAdress);

	Double getAmount();

	void setAmount(Double amount);

	Double getTransactionPrice();

	void setTransactionPrice(Double transactionPrice);

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount userAccount);

}
