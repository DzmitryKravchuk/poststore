package com.itacademy.jd2.dk.poststore.dao.api.entity.table;

public interface ICountry extends IBaseEntity {

	String getName();

	void setName(String name);

	IExpressZone getExpressZone();

	ILetterZone getLetterZone();

	IParcelZone getParcelZone();

	void setExpressZone(IExpressZone zone);

	void setLetterZone(ILetterZone zone);

	void setParcelZone(IParcelZone zone);

}
