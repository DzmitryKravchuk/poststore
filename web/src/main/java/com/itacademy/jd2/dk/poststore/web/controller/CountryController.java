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

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICountry;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ILetterZone;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IParcelZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.CountryFilter;
import com.itacademy.jd2.dk.poststore.service.ICountryService;
import com.itacademy.jd2.dk.poststore.service.IExpressZoneService;
import com.itacademy.jd2.dk.poststore.service.ILetterZoneService;
import com.itacademy.jd2.dk.poststore.service.IParcelZoneService;
import com.itacademy.jd2.dk.poststore.web.converter.CountryFromDTOConverter;
import com.itacademy.jd2.dk.poststore.web.converter.CountryToDTOConverter;
import com.itacademy.jd2.dk.poststore.web.dto.CountryDTO;
import com.itacademy.jd2.dk.poststore.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/country")
public class CountryController extends AbstractController {
	@Autowired
	private ICountryService countryService;
	@Autowired
	private IExpressZoneService expressZoneService;
	@Autowired
	private ILetterZoneService letterZoneService;
	@Autowired
	private IParcelZoneService parcelZoneService;

	@Autowired
	private CountryFromDTOConverter fromDtoConverter;

	@Autowired
	private CountryToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final CountryFilter filter = new CountryFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(countryService.getCount(filter));

		filter.setFetchExpressZone(true);
		filter.setFetchLetterZone(true);
		filter.setFetchParcelZone(true);
		final List<ICountry> entities = countryService.find(filter);
		List<CountryDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("country.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new CountryDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("country.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final CountryDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("country.edit", hashMap);
		} else {
			final ICountry entity = fromDtoConverter.apply(formModel);
			countryService.save(entity);
			return "redirect:/country";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ICountry dbModel = countryService.getFullInfo(id);
		final CountryDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("country.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final CountryDTO dto = toDtoConverter.apply(countryService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("country.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		countryService.delete(id);
		return "redirect:/country";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IExpressZone> expressZones = expressZoneService.getAll();
		final Map<Integer, Double> expressZonesMap = expressZones.stream()
				.collect(Collectors.toMap(IExpressZone::getId, IExpressZone::getPrice4g100));
		hashMap.put("expressZonesChoices", expressZonesMap);

		final List<ILetterZone> letterZones = letterZoneService.getAll();
		final Map<Integer, Double> letterZonesMap = letterZones.stream()
				.collect(Collectors.toMap(ILetterZone::getId, ILetterZone::getPrice4g100));
		hashMap.put("letterZonesChoices", letterZonesMap);

		final List<IParcelZone> parcelZones = parcelZoneService.getAll();
		final Map<Integer, Double> parcelZonesMap = parcelZones.stream()
				.collect(Collectors.toMap(IParcelZone::getId, IParcelZone::getPrice4g100));
		hashMap.put("parcelZonesChoices", parcelZonesMap);

	}

}
