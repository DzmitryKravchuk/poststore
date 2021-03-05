package com.itacademy.jd2.dk.poststore.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CourierDTO {
	private Integer id;
	private Date created;
	private Date updated;
	@Size(min = 1, max = 50)
	private String shippingFrom;
	@Size(min = 1, max = 50)
	private String shippingTo;
	@Size(min = 1, max = 50)
	private String addressee;

	private Double price;
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

	public String getShippingFrom() {
		return shippingFrom;
	}

	public void setShippingFrom(String shippingFrom) {
		this.shippingFrom = shippingFrom;
	}

	public String getShippingTo() {
		return shippingTo;
	}

	public void setShippingTo(String shippingTo) {
		this.shippingTo = shippingTo;
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

}
