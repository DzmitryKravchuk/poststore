package com.itacademy.jd2.dk.poststore.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICountry;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ILetterZone;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IParcelZone;

@Entity
public class Country extends BaseEntity implements ICountry {
	@Column
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ExpressZone.class)
	private IExpressZone expressZone;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = LetterZone.class)
	private ILetterZone letterZone;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ParcelZone.class)
	private IParcelZone parcelZone;

	public Country(String name, IExpressZone expressZone, ILetterZone letterZone, IParcelZone parcelZone, Integer id) {
		super();
		setId(id);
		this.name = name;
		this.expressZone = expressZone;
		this.letterZone = letterZone;
		this.parcelZone = parcelZone;
	}

	public Country() {
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
	public IExpressZone getExpressZone() {
		return expressZone;
	}

	@Override
	public void setExpressZone(IExpressZone expressZone) {
		this.expressZone = expressZone;
	}

	@Override
	public ILetterZone getLetterZone() {
		return letterZone;
	}

	@Override
	public void setLetterZone(ILetterZone letterZone) {
		this.letterZone = letterZone;
	}

	@Override
	public IParcelZone getParcelZone() {
		return parcelZone;
	}

	@Override
	public void setParcelZone(IParcelZone parcelZone) {
		this.parcelZone = parcelZone;
	}

	@Override
	public String toString() {
		return "Country [name=" + getName() + " expressZone=" + getExpressZone().getId() + " letterZone="
				+ getLetterZone().getId() + " parcelZone=" + getParcelZone().getId() + "]";
	}

}
