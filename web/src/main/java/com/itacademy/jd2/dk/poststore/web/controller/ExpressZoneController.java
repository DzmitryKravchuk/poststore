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

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IExpressZone;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ExpressZoneFilter;
import com.itacademy.jd2.dk.poststore.service.IExpressZoneService;
import com.itacademy.jd2.dk.poststore.web.converter.ExpressZoneFromDTOConverter;
import com.itacademy.jd2.dk.poststore.web.converter.ExpressZoneToDTOConverter;
import com.itacademy.jd2.dk.poststore.web.dto.ExpressZoneDTO;
import com.itacademy.jd2.dk.poststore.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/expressZone")
public class ExpressZoneController extends AbstractController {

	private IExpressZoneService expressZoneService;
	private ExpressZoneToDTOConverter toDtoConverter;
	private ExpressZoneFromDTOConverter fromDtoConverter;

	@Autowired
	public ExpressZoneController(IExpressZoneService expressZoneService, ExpressZoneToDTOConverter toDtoConverter,
			ExpressZoneFromDTOConverter fromDtoConverter) {
		super();
		this.expressZoneService = expressZoneService;
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

		final ExpressZoneFilter filter = new ExpressZoneFilter();
		prepareFilter(gridState, filter);

		final List<IExpressZone> entities = expressZoneService.find(filter);
		List<ExpressZoneDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(expressZoneService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("expressZone.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IExpressZone newEntity = expressZoneService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("expressZone.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final ExpressZoneDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "expressZone.edit";
		} else {
			final IExpressZone entity = fromDtoConverter.apply(formModel);
			expressZoneService.save(entity);
			return "redirect:/expressZone"; // generates 302 response
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		expressZoneService.delete(id);
		return "redirect:/expressZone";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IExpressZone dbModel = expressZoneService.get(id);
		final ExpressZoneDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("expressZone.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final ExpressZoneDTO dto = toDtoConverter.apply(expressZoneService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("expressZone.edit", hashMap);
	}
}
