package com.itacademy.jd2.dk.poststore.jdbc.impl.entity;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICountry;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ILetterZone;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IParcelZone;

public class Country extends BaseEntity implements ICountry {
	private String name;
	private IExpressZone expressZone;
	private ILetterZone letterZone;
	private IParcelZone parcelZone;

	@Override
	public String getName() {
		return name;
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
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [name=" + getName() + " expressZone=" + getExpressZone().getId() + " letterZone="
				+ getLetterZone().getId() + " parcelZone=" + getParcelZone().getId() + "]";
	}

}
