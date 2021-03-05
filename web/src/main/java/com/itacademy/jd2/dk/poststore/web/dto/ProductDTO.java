package com.itacademy.jd2.dk.poststore.web.dto;

import java.util.Date;

import javax.validation.constraints.Size;

public class ProductDTO {
	private Integer id;
	@Size(min = 1, max = 50)
	private String name;

	private Date created;

	private Date updated;

	private Double price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
