package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.ImageFormat;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPolygraphy;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.service.IPaperDetailsService;
import com.itacademy.jd2.dk.poststore.service.IPolygraphyService;
import com.itacademy.jd2.dk.poststore.service.IUserAccountService;
import com.itacademy.jd2.dk.poststore.web.dto.PolygraphyDTO;

@Component
public class PolygraphyFromDTOConverter implements Function<PolygraphyDTO, IPolygraphy> {
	@Autowired
	private IPolygraphyService polygraphyService;

	@Autowired
	private IPaperDetailsService paperDetailsService;

	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public IPolygraphy apply(PolygraphyDTO dto) {
		final IPolygraphy entity = polygraphyService.createEntity();
		entity.setId(dto.getId());
		entity.setCopyCount(dto.getCopyCount());
		entity.setIsColoured(dto.getColoured());
		entity.setIsDuplexPrinting(dto.getDuplexPrinting());
		entity.setFormat(ImageFormat.valueOf(dto.getImageFormat()));

		final IUserAccount userAccount = userAccountService.createEntity();
		userAccount.setId(dto.getUserAccountId());
		entity.setUserAccount(userAccount);

		final IPaperDetails paperDetails = paperDetailsService.get(dto.getPaperDetailsId());
		entity.setPaperDetails(paperDetails);

		return entity;
	}

}
