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

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IParcelZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ParcelZoneFilter;
import com.itacademy.jd2.dk.poststore.service.IParcelZoneService;
import com.itacademy.jd2.dk.poststore.web.converter.ParcelZoneFromDTOConverter;
import com.itacademy.jd2.dk.poststore.web.converter.ParcelZoneToDTOConverter;
import com.itacademy.jd2.dk.poststore.web.dto.ParcelZoneDTO;
import com.itacademy.jd2.dk.poststore.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/parcelZone")
public class ParcelZoneController extends AbstractController {
	@Autowired
	private IParcelZoneService parcelZoneService;
	@Autowired
	private ParcelZoneToDTOConverter toDtoConverter;
	@Autowired
	private ParcelZoneFromDTOConverter fromDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final ParcelZoneFilter filter = new ParcelZoneFilter();
		prepareFilter(gridState, filter);

		final List<IParcelZone> entities = parcelZoneService.find(filter);
		List<ParcelZoneDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(parcelZoneService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("parcelZone.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IParcelZone newEntity = parcelZoneService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("parcelZone.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final ParcelZoneDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "parcelZone.edit";
		} else {
			final IParcelZone entity = fromDtoConverter.apply(formModel);
			parcelZoneService.save(entity);
			return "redirect:/parcelZone"; // generates 302 response
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		parcelZoneService.delete(id);
		return "redirect:/parcelZone";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IParcelZone dbModel = parcelZoneService.get(id);
		final ParcelZoneDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("parcelZone.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final ParcelZoneDTO dto = toDtoConverter.apply(parcelZoneService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("parcelZone.edit", hashMap);
	}
}
