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

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;
import com.itacademy.jd2.dk.poststore.dao.api.filter.PaperDetailsFilter;
import com.itacademy.jd2.dk.poststore.service.IPaperDetailsService;
import com.itacademy.jd2.dk.poststore.web.converter.PaperDetailsFromDTOConverter;
import com.itacademy.jd2.dk.poststore.web.converter.PaperDetailsToDTOConverter;
import com.itacademy.jd2.dk.poststore.web.dto.PaperDetailsDTO;
import com.itacademy.jd2.dk.poststore.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/paperDetails")
public class PaperDetailsController extends AbstractController {

	private IPaperDetailsService paperDetailsService;

	private PaperDetailsToDTOConverter toDtoConverter;
	private PaperDetailsFromDTOConverter fromDtoConverter;

	@Autowired
	private PaperDetailsController(IPaperDetailsService productService, PaperDetailsToDTOConverter toDtoConverter,
			PaperDetailsFromDTOConverter fromDtoConverter) {
		super();
		this.paperDetailsService = productService;
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

		final PaperDetailsFilter filter = new PaperDetailsFilter();
		prepareFilter(gridState, filter);

		final List<IPaperDetails> entities = paperDetailsService.find(filter);
		List<PaperDetailsDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(paperDetailsService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("paperDetails.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IPaperDetails newEntity = paperDetailsService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("paperDetails.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final PaperDetailsDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "paperDetails.edit";
		} else {
			final IPaperDetails entity = fromDtoConverter.apply(formModel);
			paperDetailsService.save(entity);
			return "redirect:/paperDetails"; 
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		paperDetailsService.delete(id);
		return "redirect:/paperDetails";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IPaperDetails dbModel = paperDetailsService.get(id);
		final PaperDetailsDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("paperDetails.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final PaperDetailsDTO dto = toDtoConverter.apply(paperDetailsService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("paperDetails.edit", hashMap);
	}
}
