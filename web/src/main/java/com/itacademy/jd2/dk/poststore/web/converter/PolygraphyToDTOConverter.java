package com.itacademy.jd2.dk.poststore.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPolygraphy;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.web.dto.PolygraphyDTO;

@Component
public class PolygraphyToDTOConverter implements Function<IPolygraphy, PolygraphyDTO> {

	@Override
	public PolygraphyDTO apply(final IPolygraphy entity) {
		final PolygraphyDTO PolygraphyDTO = new PolygraphyDTO();

		PolygraphyDTO.setId(entity.getId());
		PolygraphyDTO.setCreated(entity.getCreated());
		PolygraphyDTO.setUpdated(entity.getUpdated());
		PolygraphyDTO.setCopyCount(entity.getCopyCount());
		PolygraphyDTO.setColoured(entity.getIsColoured());
		PolygraphyDTO.setImageFormat(entity.getFormat().name());
		PolygraphyDTO.setDuplexPrinting(entity.getIsDuplexPrinting());
		PolygraphyDTO.setPrice(entity.getPrice());

		final IUserAccount userAccount = entity.getUserAccount();
		if (userAccount != null) {
			PolygraphyDTO.setUserAccountId(userAccount.getId());
		}

		final IPaperDetails paperDetails = entity.getPaperDetails();
		if (paperDetails != null) {
			PolygraphyDTO.setPaperDetailsId(paperDetails.getId());
			PolygraphyDTO.setPaperDetailsName(paperDetails.getName());
		}

		return PolygraphyDTO;
	}

}
