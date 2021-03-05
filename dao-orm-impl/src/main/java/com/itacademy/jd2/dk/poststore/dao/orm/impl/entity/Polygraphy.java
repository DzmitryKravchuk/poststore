package com.itacademy.jd2.dk.poststore.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.ImageFormat;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPolygraphy;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;

@Entity
public class Polygraphy extends BaseEntity implements IPolygraphy {

	@Column
	@Enumerated(EnumType.STRING)
	private ImageFormat imageFormat;
	@Column
	private Integer copyCount;
	@Column
	private Boolean isColoured;
	@Column
	private Boolean isDuplexPrinting;
	@Column
	private Double price;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
	private IUserAccount userAccount;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = PaperDetails.class)
	private IPaperDetails paperDetails;

	@Override
	public ImageFormat getFormat() {
		return imageFormat;
	}

	@Override
	public void setFormat(ImageFormat format) {
		this.imageFormat = format;
	}

	@Override
	public Integer getCopyCount() {
		return copyCount;
	}

	@Override
	public void setCopyCount(Integer copyCount) {
		this.copyCount = copyCount;
	}

	@Override
	public Boolean getIsColoured() {
		return isColoured;
	}

	@Override
	public void setIsColoured(Boolean isColoured) {
		this.isColoured = isColoured;
	}

	@Override
	public Boolean getIsDuplexPrinting() {
		return isDuplexPrinting;
	}

	@Override
	public void setIsDuplexPrinting(Boolean isDuplexPrinting) {
		this.isDuplexPrinting = isDuplexPrinting;
	}

	@Override
	public Double getPrice() {
		return price;
	}

	@Override
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public IPaperDetails getPaperDetails() {
		return paperDetails;
	}

	@Override
	public void setPaperDetails(IPaperDetails paper) {
		this.paperDetails = paper;
	}

	@Override
	public String toString() {
		return "Poligraphy [getId()=" + getId() + ", getFormat()=" + getFormat() + ", getCopyCount()=" + getCopyCount()
				+ ", getPrice()=" + getPrice() + ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated()
				+ "]";
	}

}
