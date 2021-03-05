package com.itacademy.jd2.dk.poststore.dao.orm.impl;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.dk.poststore.dao.api.IUserAccountDetailsDao;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccountDetails;
import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.UserAccountDetails;

@Repository
public class UserAccountDetailsDaoImpl extends AbstractDaoImpl<IUserAccountDetails, Integer>
		implements IUserAccountDetailsDao {

	protected UserAccountDetailsDaoImpl() {
		super(UserAccountDetails.class);
	}

	@Override
	public IUserAccountDetails createEntity() {
		return new UserAccountDetails();
	}

}
