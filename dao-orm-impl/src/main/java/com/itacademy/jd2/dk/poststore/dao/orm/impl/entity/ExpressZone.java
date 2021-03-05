package com.itacademy.jd2.dk.poststore.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;

@Entity
public class ExpressZone extends BaseEntity implements IExpressZone {

	@Column
	private Double price4g100; // indicates tariff for every 100g of certain mailing type due
	// to each country

	public ExpressZone(Double price4g100, Integer id) {
		super();
		setId(id);
		this.price4g100 = price4g100;
	}

	public ExpressZone() {
	}

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
		return "ExpressZone [getId()=" + getId() + ", getPrice4g100()" + getPrice4g100() + ", getCreated()="
				+ getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}
}
