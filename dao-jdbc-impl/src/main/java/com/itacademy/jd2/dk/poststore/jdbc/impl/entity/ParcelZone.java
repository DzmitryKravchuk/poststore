package com.itacademy.jd2.dk.poststore.jdbc.impl.entity;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IParcelZone;

public class ParcelZone extends BaseEntity implements IParcelZone {
	private Double price4g100; // indicates tariff for every 100g (1000g) of certain mailing type due
	// to each country

	@Override
	public Double getPrice4g100() {
		return price4g100;
	}

	@Override
	public void setPrice4g100(Double price4g100) {
		this.price4g100 = price4g100;
	}

	@Override
	public String toString() {
		return "ParcelZone [getId()=" + getId() + ", getPrice4g100()" + getPrice4g100() + ", getCreated()="
				+ getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}

}
