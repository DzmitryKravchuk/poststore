package com.itacademy.jd2.dk.poststore.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class PolygraphyDTO {

	private Integer id;
	private Date created;
	private Date updated;

	@NotNull
	private String imageFormat;

	@NotNull
	private Integer copyCount;

	private Boolean duplexPrinting;

	private Boolean coloured;

	@NotNull
	private Integer userAccountId;

	@NotNull
	private Integer paperDetailsId;
	private String paperDetailsName;

	private Double price;

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

	public String getImageFormat() {
		return imageFormat;
	}

	public void setImageFormat(String imageFormat) {
		this.imageFormat = imageFormat;
	}

	public Integer getCopyCount() {
		return copyCount;
	}

	public void setCopyCount(Integer copyCount) {
		this.copyCount = copyCount;
	}

	public Boolean getDuplexPrinting() {
		return duplexPrinting;
	}

	public void setDuplexPrinting(Boolean duplexPrinting) {
		this.duplexPrinting = duplexPrinting;
	}

	public Boolean getColoured() {
		return coloured;
	}

	public void setColoured(Boolean coloured) {
		this.coloured = coloured;
	}

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

	public Integer getPaperDetailsId() {
		return paperDetailsId;
	}

	public void setPaperDetailsId(Integer paperDetailsId) {
		this.paperDetailsId = paperDetailsId;
	}

	public String getPaperDetailsName() {
		return paperDetailsName;
	}

	public void setPaperDetailsName(String paperDetailsName) {
		this.paperDetailsName = paperDetailsName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
