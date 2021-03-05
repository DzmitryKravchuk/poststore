package com.itacademy.jd2.dk.poststore.web.dto;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.itacademy.jd2.dk.poststore.dao.orm.impl.entity.UserAccountDetails;

public class UserAccountDTO {
	private Integer id;
	private Date created;
	private Date updated;

	@Size(min = 1, max = 50)
	private String eMail;

	@Size(min = 1, max = 50)
	private String password;

	private String userRole;

	@NotNull
	@Valid
	private UserAccountDetails details = new UserAccountDetails();

	public UserAccountDetails getDetails() {
		return details;
	}

	public void setDetails(final UserAccountDetails details) {
		this.details = details;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(final Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(final Date updated) {
		this.updated = updated;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(final String eMail) {
		this.eMail = eMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(final String userRole) {
		this.userRole = userRole;
	}

}
