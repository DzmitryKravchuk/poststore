package com.itacademy.jd2.dk.poststore.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;

@Entity
public class PaperDetails extends BaseEntity implements IPaperDetails {

	@Column
	private String name;

	@Column
	private Double price; // price for a standard sheet A4 formatted paper

	public PaperDetails(String name, Double price4paper, Integer id) {
		super();
		setId(id);
		this.name = name;
		this.price = price4paper;
	}

	public PaperDetails() {
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Double getPrice4Paper() {
		return price;
	}

	@Override
	public void setPrice4Paper(Double price4Paper) {
		this.price = price4Paper;
	}

	@Override
	public String toString() {
		return "Paper [brand=" + getName() + ", getId()=" + getId() + ", getPrice4Paper()=" + getPrice4Paper()
				+ ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}
}
