package com.itacademy.jd2.dk.poststore.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MoneyTransferDTO {
	private Integer id;
	private Date created;
	private Date updated;
	@Size(min = 1, max = 50)
	private String payerName;
	@Size(min = 1, max = 50)
	private String payerAdress;
	@Size(min = 1, max = 50)
	private String beneficiaryName;
	@Size(min = 1, max = 50)
	private String beneficiaryAdress;
	@NotNull
	private Double amount;

	private Double transactionPrice;
	@NotNull
	private Integer userAccountId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayerAdress() {
		return payerAdress;
	}

	public void setPayerAdress(String payerAdress) {
		this.payerAdress = payerAdress;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getBeneficiaryAdress() {
		return beneficiaryAdress;
	}

	public void setBeneficiaryAdress(String beneficiaryAdress) {
		this.beneficiaryAdress = beneficiaryAdress;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getTransactionPrice() {
		return transactionPrice;
	}

	public void setTransactionPrice(Double transactionPrice) {
		this.transactionPrice = transactionPrice;
	}

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

}
