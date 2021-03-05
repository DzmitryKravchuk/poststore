package com.itacademy.jd2.dk.poststore.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ILetterZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.LetterZoneFilter;
import com.itacademy.jd2.dk.poststore.service.ILetterZoneService;
import com.itacademy.jd2.dk.poststore.web.converter.LetterZoneFromDTOConverter;
import com.itacademy.jd2.dk.poststore.web.converter.LetterZoneToDTOConverter;
import com.itacademy.jd2.dk.poststore.web.dto.LetterZoneDTO;
import com.itacademy.jd2.dk.poststore.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/letterZone")
public class LetterZoneController extends AbstractController {

	private ILetterZoneService letterZoneService;
	private LetterZoneToDTOConverter toDtoConverter;
	private LetterZoneFromDTOConverter fromDtoConverter;

	@Autowired
	public LetterZoneController(ILetterZoneService letterZoneService, LetterZoneToDTOConverter toDtoConverter,
			LetterZoneFromDTOConverter fromDtoConverter) {
		super();
		this.letterZoneService = letterZoneService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {
		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final LetterZoneFilter filter = new LetterZoneFilter();
		prepareFilter(gridState, filter);

		final List<ILetterZone> entities = letterZoneService.find(filter);
		List<LetterZoneDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(letterZoneService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("letterZone.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ILetterZone newEntity = letterZoneService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("letterZone.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final LetterZoneDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "letterZone.edit";
		} else {
			final ILetterZone entity = fromDtoConverter.apply(formModel);
			letterZoneService.save(entity);
			return "redirect:/letterZone"; // generates 302 response
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		letterZoneService.delete(id);
		return "redirect:/letterZone";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ILetterZone dbModel = letterZoneService.get(id);
		final LetterZoneDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("letterZone.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final LetterZoneDTO dto = toDtoConverter.apply(letterZoneService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("letterZone.edit", hashMap);
	}
}
