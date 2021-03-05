package com.itacademy.jd2.dk.poststore.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryDTO {
	private Integer id;
	private Date created;
	private Date updated;

	@Size(min = 1, max = 50)
	private String name;
	@NotNull
	private Integer expressZoneId;
	private Double expressPrice;
	
	@NotNull
	private Integer letterZoneId;
	private Double letterPrice;
	
	@NotNull
	private Integer parcelZoneId;
	private Double parcelPrice;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getExpressZoneId() {
		return expressZoneId;
	}

	public void setExpressZoneId(Integer expressZoneId) {
		this.expressZoneId = expressZoneId;
	}

	public  Double getExpressPrice() {
		return expressPrice;
	}

	public  void setExpressPrice(Double expressPrice) {
		this.expressPrice = expressPrice;
	}

	public Integer getLetterZoneId() {
		return letterZoneId;
	}

	public void setLetterZoneId(Integer letterZoneId) {
		this.letterZoneId = letterZoneId;
	}

	public  Double getLetterPrice() {
		return letterPrice;
	}

	public  void setLetterPrice(Double letterPrice) {
		this.letterPrice = letterPrice;
	}

	public Integer getParcelZoneId() {
		return parcelZoneId;
	}

	public void setParcelZoneId(Integer parcelZoneId) {
		this.parcelZoneId = parcelZoneId;
	}

	public  Double getParcelPrice() {
		return parcelPrice;
	}

	public  void setParcelPrice(Double parcelPrice) {
		this.parcelPrice = parcelPrice;
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
