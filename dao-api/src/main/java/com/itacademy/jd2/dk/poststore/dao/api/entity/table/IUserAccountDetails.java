package com.itacademy.jd2.dk.poststore.dao.api.entity.table;

public interface IUserAccountDetails extends IBaseEntity {

	String getName();

	void setName(String name);

	String getAdress();

	void setAdress(String adress);

	Integer getPhone();

	void setPhone(Integer phone);

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount userAccount);

}
