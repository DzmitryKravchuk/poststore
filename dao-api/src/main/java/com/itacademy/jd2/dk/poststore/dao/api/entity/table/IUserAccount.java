package com.itacademy.jd2.dk.poststore.dao.api.entity.table;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.UserRole;

public interface IUserAccount extends IBaseEntity {

	UserRole getUserRole();

	void setUserRole(UserRole userRole);

	String geteMail();

	String getPassword();

	void seteMail(String eMail);

	void setPassword(String password);

	IUserAccountDetails getUserAccountDetails();

	void setUserAccountDetails(IUserAccountDetails userAccountDetails);

}
