package com.itacademy.jd2.dk.poststore.web.controller;

import java.util.Arrays;
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

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.MailingType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.ICountry;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMailing;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.filter.MailingFilter;
import com.itacademy.jd2.dk.poststore.service.ICountryService;
import com.itacademy.jd2.dk.poststore.service.IMailingService;
import com.itacademy.jd2.dk.poststore.service.IUserAccountService;
import com.itacademy.jd2.dk.poststore.web.converter.MailingFromDTOConverter;
import com.itacademy.jd2.dk.poststore.web.converter.MailingToDTOConverter;
import com.itacademy.jd2.dk.poststore.web.dto.MailingDTO;
import com.itacademy.jd2.dk.poststore.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.dk.poststore.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/mailing")
public class MailingController extends AbstractController {

	@Autowired
	private IMailingService mailingService;

	@Autowired
	private ICountryService countryService;
	@Autowired
	private IUserAccountService userAccountService;

	@Autowired
	private MailingFromDTOConverter fromDtoConverter;

	@Autowired
	private MailingToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		Integer userAccountId = AuthHelper.getLoggedUserId();

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "updated");

		final MailingFilter filter = new MailingFilter();
		filter.setUserAccountId(userAccountId);

		prepareFilter(gridState, filter);
		gridState.setTotalCount(mailingService.getCount(filter));

		filter.setFetchCountry(true);
		filter.setFetchUserAccount(true);

		final List<IMailing> entities = mailingService.find(filter); // searching for all entities

		/*
		 * List<IMailing> myEntities = new ArrayList<IMailing>();// choosing one's own
		 * entities for (IMailing entity : entities) {
		 * 
		 * if (entity.getUserAccount().getId() == userAccountId) {
		 * myEntities.add(entity); } }
		 */
		List<MailingDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("mailing.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		Integer userAccountId = AuthHelper.getLoggedUserId();

		final Map<String, Object> hashMap = new HashMap<>();
		final MailingDTO dto = new MailingDTO();
		dto.setWeight(0.1); // set default minimum weight
		dto.setUserAccountId(userAccountId);
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("mailing.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final MailingDTO formModel, final BindingResult result) {

		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);

			loadCommonFormModels(hashMap);
			return new ModelAndView("mailing.edit", hashMap);
		} else {
			final IMailing entity = fromDtoConverter.apply(formModel);

			mailingService.save(entity);
			return "redirect:/mailing";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {

		final IMailing dbModel = mailingService.getFullInfo(id);
		final MailingDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("mailing.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final MailingDTO dto = toDtoConverter.apply(mailingService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("mailing.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		mailingService.delete(id);
		return "redirect:/mailing";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IUserAccount> userAccounts = userAccountService.getAll();
		final Map<Integer, String> orderrsMap = userAccounts.stream()
				.collect(Collectors.toMap(IUserAccount::getId, IUserAccount::geteMail));
		hashMap.put("usersChoices", orderrsMap);

		final List<ICountry> countries = countryService.getAll();
		final Map<Integer, String> countriessMap = countries.stream()
				.collect(Collectors.toMap(ICountry::getId, ICountry::getName));
		hashMap.put("countriesChoices", countriessMap);

		final List<MailingType> mailingTypesList = Arrays.asList(MailingType.values());
		final Map<String, String> mailingTypesMap = mailingTypesList.stream()
				.collect(Collectors.toMap(MailingType::name, MailingType::name));
		hashMap.put("mailingTypesChoices", mailingTypesMap);

	}

}
