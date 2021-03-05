package com.itacademy.jd2.dk.poststore.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class ParcelZoneDTO {
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

	public synchronized Integer getId() {
		return id;
	}

	public synchronized void setId(Integer id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public synchronized void setCreated(Date created) {
		this.created = created;
	}

	public synchronized Date getUpdated() {
		return updated;
	}

	public synchronized void setUpdated(Date updated) {
		this.updated = updated;
	}

}
