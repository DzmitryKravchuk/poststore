package com.itacademy.jd2.dk.poststore.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class ExpressZoneDTO {

	private Integer id;
	private Date created;
	private Date updated;

	@NotNull
	private Double price4g100;

	public Double getPrice4g100() {
		return price4g100;
	}

	public void setPrice4g100(Double price4g100) {
		this.price4g100 = price4g100;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
