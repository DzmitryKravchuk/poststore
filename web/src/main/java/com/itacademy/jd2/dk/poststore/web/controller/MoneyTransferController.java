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

import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IMoneyTransfer;
import com.itacademy.jd2.dk.poststore.dao.api.filter.MoneyTransferFilter;
import com.itacademy.jd2.dk.poststore.service.IMoneyTransferService;
import com.itacademy.jd2.dk.poststore.web.converter.MoneyTransferFromDTOConverter;
import com.itacademy.jd2.dk.poststore.web.converter.MoneyTransferToDTOConverter;
import com.itacademy.jd2.dk.poststore.web.dto.MoneyTransferDTO;
import com.itacademy.jd2.dk.poststore.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.dk.poststore.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/moneyTransfer")
public class MoneyTransferController extends AbstractController {

	@Autowired
	private IMoneyTransferService moneyTransferService;

	@Autowired
	private MoneyTransferFromDTOConverter fromDtoConverter;

	@Autowired
	private MoneyTransferToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		Integer userAccountId = AuthHelper.getLoggedUserId();

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "updated");

		final MoneyTransferFilter filter = new MoneyTransferFilter();
		filter.setUserAccountId(userAccountId);

		prepareFilter(gridState, filter);
		gridState.setTotalCount(moneyTransferService.getCount(filter));

		filter.setFetchUserAccount(true);

		final List<IMoneyTransfer> entities = moneyTransferService.find(filter);

		List<MoneyTransferDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("moneyTransfer.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		Integer userAccountId = AuthHelper.getLoggedUserId();
		final Map<String, Object> hashMap = new HashMap<>();
		final MoneyTransferDTO dto = new MoneyTransferDTO();
		dto.setUserAccountId(userAccountId);
		hashMap.put("formModel", dto);
		return new ModelAndView("moneyTransfer.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final MoneyTransferDTO formModel,
			final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			return new ModelAndView("courier.edit", hashMap);
		} else {
			final IMoneyTransfer entity = fromDtoConverter.apply(formModel);
			moneyTransferService.save(entity);
			return "redirect:/moneyTransfer";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IMoneyTransfer dbModel = moneyTransferService.getFullInfo(id);
		final MoneyTransferDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		return new ModelAndView("moneyTransfer.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final MoneyTransferDTO dto = toDtoConverter.apply(moneyTransferService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		return new ModelAndView("moneyTransfer.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		moneyTransferService.delete(id);
		return "redirect:/moneyTransfer";
	}

}
