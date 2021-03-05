package com.itacademy.jd2.dk.poststore.web.dto;

import javax.validation.constraints.Size;

public class UserAccountDetailsDTO {

	@Size(min = 1, max = 50)
	private String name;

	@Size(min = 1, max = 50)
	private String adress;

	@Size(min = 9, max = 13)
	private Integer phone;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(final String adress) {
		this.adress = adress;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(final Integer phone) {
		this.phone = phone;
	}

}
