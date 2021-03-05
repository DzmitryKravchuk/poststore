package com.itacademy.jd2.dk.poststore.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMoneyTransfer;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;

@Entity
public class MoneyTransfer extends BaseEntity implements IMoneyTransfer {

	@Column
	private String beneficiaryName;
	@Column
	private String beneficiaryAdress;
	@Column
	private Double amount;
	@Column
	private Double transactionPrice;
	@Column
	private String payerName;
	@Column
	private String payerAdress;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
	private IUserAccount userAccount;

	@Override
	public String getPayerName() {
		return payerName;
	}

	@Override
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	@Override
	public String getPayerAdress() {
		return payerAdress;
	}

	@Override
	public void setPayerAdress(String payerAdress) {
		this.payerAdress = payerAdress;
	}

	@Override
	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	@Override
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	@Override
	public String getBeneficiaryAdress() {
		return beneficiaryAdress;
	}

	@Override
	public void setBeneficiaryAdress(String beneficiaryAdress) {
		this.beneficiaryAdress = beneficiaryAdress;
	}

	@Override
	public Double getAmount() {
		return amount;
	}

	@Override
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public Double getTransactionPrice() {
		return transactionPrice;
	}

	@Override
	public void setTransactionPrice(Double transactionPrice) {
		this.transactionPrice = transactionPrice;
	}

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public String toString() {
		return "MoneyTransfer [getId()=" + getId() + ", getPayerName()=" + getPayerName() + ", getPayerAdress()"
				+ getPayerAdress() + ", getBeneficiaryName()=" + getBeneficiaryName() + ", getBeneficiaryAdress()="
				+ getBeneficiaryAdress() + ", getAmount()=" + getAmount() + ", getPrice()=" + getTransactionPrice()
				+ ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}
}
