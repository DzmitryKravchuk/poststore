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

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICourier;
import com.itacademy.jd2.dk.poststore.dao.api.filter.CourierFilter;
import com.itacademy.jd2.dk.poststore.service.ICourierService;
import com.itacademy.jd2.dk.poststore.web.converter.CourierFromDTOConverter;
import com.itacademy.jd2.dk.poststore.web.converter.CourierToDTOConverter;
import com.itacademy.jd2.dk.poststore.web.dto.CourierDTO;
import com.itacademy.jd2.dk.poststore.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.dk.poststore.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/courier")
public class CourierController extends AbstractController {

	@Autowired
	private ICourierService courierService;

	@Autowired
	private CourierFromDTOConverter fromDtoConverter;

	@Autowired
	private CourierToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		Integer userAccountId = AuthHelper.getLoggedUserId();

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "updated");

		final CourierFilter filter = new CourierFilter();
		filter.setUserAccountId(userAccountId);
		prepareFilter(gridState, filter);
		gridState.setTotalCount(courierService.getCount(filter));

		filter.setFetchUserAccount(true);

		final List<ICourier> entities = courierService.find(filter);

		List<CourierDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("courier.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		Integer userAccountId = AuthHelper.getLoggedUserId();
		final Map<String, Object> hashMap = new HashMap<>();
		final CourierDTO dto = new CourierDTO();
		dto.setUserAccountId(userAccountId);
		hashMap.put("formModel", dto);
		return new ModelAndView("courier.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final CourierDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			return new ModelAndView("courier.edit", hashMap);
		} else {
			final ICourier entity = fromDtoConverter.apply(formModel);
			courierService.save(entity);
			return "redirect:/courier";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ICourier dbModel = courierService.getFullInfo(id);
		final CourierDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		return new ModelAndView("courier.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final CourierDTO dto = toDtoConverter.apply(courierService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		return new ModelAndView("courier.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		courierService.delete(id);
		return "redirect:/courier";
	}

}
