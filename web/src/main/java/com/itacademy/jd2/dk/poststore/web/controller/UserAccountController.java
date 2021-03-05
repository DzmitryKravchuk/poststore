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

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.dk.poststore.service.IUserAccountService;
import com.itacademy.jd2.dk.poststore.web.converter.UserAccountFromDTOConverter;
import com.itacademy.jd2.dk.poststore.web.converter.UserAccountToDTOConverter;
import com.itacademy.jd2.dk.poststore.web.dto.UserAccountDTO;
import com.itacademy.jd2.dk.poststore.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/userAccount")
public class UserAccountController extends AbstractController {

	@Autowired
	private IUserAccountService userAccountService;
	@Autowired
	private UserAccountToDTOConverter toDtoConverter;
	@Autowired
	private UserAccountFromDTOConverter fromDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {
		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final UserAccountFilter filter = new UserAccountFilter();
		prepareFilter(gridState, filter);

		final List<IUserAccount> entities = userAccountService.find(filter);
		List<UserAccountDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(userAccountService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("userAccount.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final UserAccountDTO dto = new UserAccountDTO();
		dto.setUserRole("customer"); // set default value
		hashMap.put("formModel", dto);
		return new ModelAndView("userAccount.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final UserAccountDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "userAccount.edit";
		} else {
			final IUserAccount entity = fromDtoConverter.apply(formModel);
			userAccountService.save(entity);
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		userAccountService.delete(id);
		return "redirect:/userAccount";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IUserAccount dbModel = userAccountService.get(id);
		final UserAccountDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		return new ModelAndView("userAccount.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final UserAccountDTO dto = toDtoConverter.apply(userAccountService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		return new ModelAndView("userAccount.edit", hashMap);
	}

}
