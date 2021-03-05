package com.itacademy.jd2.dk.poststore.dao.api.entity.table;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.ImageFormat;

public interface IPolygraphy extends IBaseEntity {

	ImageFormat getFormat();

	void setFormat(ImageFormat format);

	Integer getCopyCount();

	void setCopyCount(Integer copyCount);

	Boolean getIsColoured();

	void setIsColoured(Boolean coloured);

	Double getPrice();

	void setPrice(Double price);

	IPaperDetails getPaperDetails();

	void setPaperDetails(IPaperDetails paper);

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount userAccount);

	Boolean getIsDuplexPrinting();

	void setIsDuplexPrinting(Boolean isDuplexPrinting);

}
