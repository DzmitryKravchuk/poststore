package com.itacademy.jd2.dk.poststore.jdbc.impl.entity;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;

public class PaperDetails extends BaseEntity implements IPaperDetails {
	private String brand;
	private String details;
	private Double price4A4; // price for a standard sheet A4 formatted paper

	@Override
	public String toString() {
		return "Paper [brand=" + getName() + ", getId()=" + getId() + ", getPrice4A4()=" + getPrice4Paper()
				+ ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}

	@Override
	public Double getPrice4Paper() {
		return price4A4;
	}

	@Override
	public void setPrice4Paper(Double price4Paper) {
		this.price4A4 = price4Paper;

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String brand) {
		// TODO Auto-generated method stub

	}
}
